package org.example.ratelimiter;

import org.example.NoImplementationException;

import java.util.concurrent.TimeUnit;

public class BurstySmoothRateLimiter implements RateLimiter {
    private static final long NANO_PER_SEC = 1000000000;
    private final Object mLock = new Object();
    private final int mMaxStoredPermits;
    private int mStoredPermits = 0;
    private final double mRequiredSmoothTickDuration;
    private double mNextFreeAvailableTime = 0;

    public BurstySmoothRateLimiter(int tps, int maxStoredPermits) {
        this.mMaxStoredPermits = maxStoredPermits;
        this.mRequiredSmoothTickDuration = (double) NANO_PER_SEC / tps;
    }

    @Override
    public boolean acquire(int permits) {
        synchronized (mLock) {
            if (permits <= 0) {
                return false;
            }
            long now = System.nanoTime();
            double nextAvailableTimeNanos = reservePermitsLocked(permits, now);
            double wait = Math.max(nextAvailableTimeNanos - now, 0);
            sleepWithoutInterruptions((long) wait);
            return true;
        }
    }

    /**
     * Reserves us 'n' permits and returns the earliest when these permits could be used.
     * If a caller asks for more permits than max transactions per second, we do not stop per se but, the upcoming
     * transactions get delayed for the required amount of time.
     * @param permits permits asked for
     * @param now current time in nanos
     * @return Earliest time when 'n' permits could be used.
     */
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

    /**
     * If the current time has crossed the next free time for the limiter, force it to execute now.
     * Calculate the permits that are acquired during this time and cap it to the max permits.
     * @param now Current time in millis
     * @return Next free available slot.
     */
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
    public boolean acquire() {
        throw new NoImplementationException();
    }
}
