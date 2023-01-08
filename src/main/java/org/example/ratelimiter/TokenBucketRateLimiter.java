package org.example.ratelimiter;

/**
 * Implementation of {@link RateLimiter} that limits the number of calls to a predefined rate(transactions/sec). Any transaction(s)
 * over the specified limit is ignored.
 * This implementation does not smoothen out the rates and hence, could not control the sudden bursts of requests.
 * Only guarantee that it provides is on the lines of how many transaction can happen in a second.
 */
public class TokenBucketRateLimiter implements RateLimiter {

    private static final long NANO_PER_SEC = 1000000000;

    private final int mTPS;
    private long mLastExecutionNanos;
    private long mNextSecondBoundary;
    private long mCounter;

    private final Object mLock = new Object();

    public TokenBucketRateLimiter(int rate) {
        this.mTPS = rate;
        this.mCounter = 0;
        this.mLastExecutionNanos = 0L;
        this.mNextSecondBoundary = 0L;
    }

    @Override
    public boolean throttle(Code code) {
        if (mTPS == 0) {
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

    private void invoke(Code code) {
        try {
            code.invoke();
        } catch (Throwable th) {
            // Ssssh!
        }
    }

    @Override
    public void entry() {
        throw new NoImplementationException();
    }

    @Override
    public boolean exit() {
        throw new NoImplementationException();
    }
}
