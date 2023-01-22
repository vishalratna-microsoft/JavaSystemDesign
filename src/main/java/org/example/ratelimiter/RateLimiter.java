package org.example.ratelimiter;

/**
 * Rate limiter helps in limiting the rate of execution of a piece of code. The rate is defined in terms of
 * TPS(Transactions per second). Rate of 5 would suggest, 5 transactions/second. Transaction could be a DB call, API call,
 * or a simple function call.
 * <p>
 * Every {@link RateLimiter} implementation should implement either {@link RateLimiter#throttle(Code)} or, {@link RateLimiter#enter()}.
 * They can also choose to implement all.
 * <p>
 * {@link Code} represents a piece of code that needs to be rate limited. It could be a function call, if the code to be rate limited
 * spreads across multiple functions, we need to use entry() and exit() contract.
 */
public interface RateLimiter {

    /**
     * Rate limits the code passed inside as an argument.
     *
     * @param code representation of the piece of code that needs to be rate limited.
     * @return true if executed, false otherwise.
     */
    boolean throttle(Code code);

    /**
     * When the piece of code that needs to be rate limited cannot be represented as a contiguous
     * code, then entry() should be used before we start executing the code. This brings the code inside the rate
     * limiting boundaries.
     *
     * @return true if the code will execute and false if rate limited.
     * <p
     */
    boolean enter();

    /**
     * Allows multiple permits at the same time. If an expensive task takes n permits, the further calls should take the
     * toll on the rate.
     * @param permits Permits required.
     * @return true, if successful, false otherwise.
     */
    boolean enter(int permits);

    /**
     * Interface to represent a contiguous piece of code that needs to be rate limited.
     */
    interface Code {
        /**
         * Calling this function should execute the code that is delegated to this interface.
         */
        void invoke();
    }
}
