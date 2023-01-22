package org.example.ratelimiter;

import java.util.concurrent.TimeUnit;

public class BurstySmoothRateLimiter implements RateLimiter {
    private static final long NANO_PER_SEC = 1000000000;
    private final Object mLock = new Object();
    private final int mMaxStoredPermits;
    private int mStoredPermits = 0;
    private final double mRequiredSmoothTickDuration;
    private double mNextFreeAvailableTime = 0;

    public BurstySmoothRateLimiter(int permits, int maxStoredPermits) {
        this.mMaxStoredPermits = maxStoredPermits;
        this.mRequiredSmoothTickDuration = (double) NANO_PER_SEC / permits;
    }

    @Override
    public boolean enter(int permits) {
        synchronized (mLock) {
            if (permits <= 0) {
                return false;
            }
            long now = System.nanoTime();
            double available = reservePermitsLocked(permits, now);
            double wait = Math.max(available - now, 0);
            sleepWithoutInterruptions((long) wait);
            return true;
        }
    }

    private double reservePermitsLocked(int permits, long now) {
        int freshPermitsUsed = 0;
        double nextFreeAvailable = syncLocked(now);
        int storePermitsUsed = Math.max(0, Math.min(permits, mStoredPermits));
        // If we use all stored permits, then we have demanded >= storedPermits.
        if (storePermitsUsed == mStoredPermits) {
            freshPermitsUsed = permits - storePermitsUsed;
        }

        double wait = freshPermitsUsed * mRequiredSmoothTickDuration;
        // Reduce the number of available permits
        mStoredPermits -= storePermitsUsed;
        mNextFreeAvailableTime += wait;
        // Fresh permits will encounter throttling and stored permits will fire in burst to cover under utilization.
        return nextFreeAvailable;
    }

    private double syncLocked(long now) {
        if (now > mNextFreeAvailableTime) {
            // Sync potentially available permits.
            int freshPermits = (int) ((now - mNextFreeAvailableTime) / mRequiredSmoothTickDuration);
            mStoredPermits = Math.min(mStoredPermits + freshPermits, mMaxStoredPermits);
            mNextFreeAvailableTime = now;
        }
        return mNextFreeAvailableTime;
    }

    private void sleepWithoutInterruptions(long sleepNanos) {
        long remaining = sleepNanos;
        long end = System.nanoTime() + remaining;
        boolean interrupted = false;
        while (true) {
            try {
                TimeUnit.NANOSECONDS.sleep(remaining);
                return;
            } catch (InterruptedException exception) {
                interrupted = true;
                remaining = end - System.nanoTime();
            } finally {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Override
    public boolean throttle(Code code) {
        throw new NoImplementationException();
    }

    @Override
    public boolean enter() {
        throw new NoImplementationException();
    }
}
