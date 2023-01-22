package org.example.ratelimiter;

/**
 * Implementation of {@link RateLimiter} that limits the number of calls to a predefined rate(transactions/sec). Any transaction(s)
 * over the specified limit is ignored.
 * This implementation does not smoothen out the rates and hence, could not control the sudden bursts of requests.
 * Only guarantee that it provides is on the lines of how many transaction can happen in a second.
 */
public class SimpleTokenBucketRateLimiter implements RateLimiter {

    private static final long NANO_PER_SEC = 1000000000;

    private final int mTPS;
    private long mLastExecutionNanos;
    private long mNextSecondBoundary;
    private long mCounter;

    private final Object mLock = new Object();
    private final Object mBoundaryLock = new Object();

    public SimpleTokenBucketRateLimiter(int rate) {
        this.mTPS = rate;
        this.mCounter = 0;
        this.mLastExecutionNanos = 0L;
        this.mNextSecondBoundary = 0L;
    }

    // Suboptimal implementation
    @Override
    public boolean throttle(Code code) {
        if (mTPS <= 0) {
            // We do not want anything to pass.
            return false;
        }

        synchronized (mLock) {
            if (mLastExecutionNanos == 0L) {
                mCounter++;
                mLastExecutionNanos = System.nanoTime();
                mNextSecondBoundary = mLastExecutionNanos + NANO_PER_SEC;
                invoke(code);
                return true;
            } else {
                long now = System.nanoTime();
                if (now <= mNextSecondBoundary) {
                    if (mCounter < mTPS) {
                        mLastExecutionNanos = now;
                        mCounter++;
                        invoke(code);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    // Reset the counter as we in a different second now.
                    mCounter = 0;
                    mLastExecutionNanos = 0L;
                    mNextSecondBoundary = 0L;
                    return throttle(code);
                }
            }
        }
    }

    @Override
    public boolean acquire() {
        if (mTPS == 0L) {
            return false;
        }

        synchronized (mBoundaryLock) {
            if (mLastExecutionNanos == 0L) {
                mLastExecutionNanos = System.nanoTime();
                mCounter++;
                mNextSecondBoundary = mLastExecutionNanos + NANO_PER_SEC;
                return true;
            } else {
                long now = System.nanoTime();
                if (now <= mNextSecondBoundary) {
                    if (mCounter < mTPS) {
                        mLastExecutionNanos = now;
                        mCounter++;
                        return true;
                    } else return false;
                } else {
                    // Reset the counter as we in a different second now.
                    mCounter = 0;
                    mLastExecutionNanos = 0L;
                    mNextSecondBoundary = 0L;
                    return acquire();
                }
            }
        }
    }

    @Override
    public boolean acquire(int permits) {
        throw new NoImplementationException();
    }

    private void invoke(Code code) {
        try {
            code.invoke();
        } catch (Throwable th) {
            // Ssssh!
        }
    }
}
