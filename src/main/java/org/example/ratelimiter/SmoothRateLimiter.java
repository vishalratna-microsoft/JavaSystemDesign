package org.example.ratelimiter;

import org.example.NoImplementationException;

import java.util.concurrent.TimeUnit;

/**
 * This implementation makes sure that the constant rate is maintained throughout the execution.
 * It maintains an internal timing whose ticks are determined by the transactions per second. If TPS is 5, then
 * it means we need to execute each call with a gap of 200 ms. Below diagram demonstrates that.
 * <p>
 * 0              1              2              3              4              5              6
 * |--[]--[]--[]--|--[]--[]--[]--|--[]--[]--[]--|--[]--[]--[]--|--[]--[]--[]--|--[]--[]--[]--|
 * <p>
 * If a request to execute the transaction comes in between 200ms to 400ms, say at 300ms, then the rate limiter makes
 * the request to wait for next 100ms. Till that time, it makes the request thread to sleep.
 * This implementation does not drop the requests as opposed to {@link SimpleTokenBucketRateLimiter}
 */
public class SmoothRateLimiter implements RateLimiter {
    private static final long NANO_PER_SEC = 1000000000;
    private final int mTPS;
    private final double mRequiredSmoothTickDuration;
    private long mLastExecutionNanos = 0L;
    private final Object mLock = new Object();
    private double mNextFreeAvailableTime = 0.0;

    public SmoothRateLimiter(int rate) {
        this.mTPS = rate;
        this.mRequiredSmoothTickDuration = (double) NANO_PER_SEC / mTPS;
    }

    @Override
    public boolean throttle(Code code) {
        throw new NoImplementationException();
    }

    @Override
    public boolean acquire() {
        synchronized (mLock) {
            if (mLastExecutionNanos == 0L) {
                // First execution for the instance.
                if (mTPS <= 0) {
                    return false;
                } else {
                    mLastExecutionNanos = System.nanoTime();
                    mNextFreeAvailableTime = (double) mLastExecutionNanos + mRequiredSmoothTickDuration;
                    return true;
                }
            } else {
                long now = System.nanoTime();
                if (now <= mNextFreeAvailableTime) {
                    long wait = (long) (mNextFreeAvailableTime - now);
                    sleepWithoutInterruptions(wait);
                } else {
                    int ticks = (int) ((now - mLastExecutionNanos) / mRequiredSmoothTickDuration);
                    long nextTickNanos = (long) (mLastExecutionNanos + ((ticks + 1) * mRequiredSmoothTickDuration));
                    long wait = nextTickNanos - now;
                    sleepWithoutInterruptions(wait);
                }
                mLastExecutionNanos = System.nanoTime();
                mNextFreeAvailableTime = (double) mLastExecutionNanos + mRequiredSmoothTickDuration;
                return true;
            }
        }
    }

    @Override
    public boolean acquire(int permits) {
        throw new NoImplementationException();
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
}

