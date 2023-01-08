package org.example.ratelimiter;

/**
 * Rate limiter helps in limiting the rate of execution of a piece of code. The rate is defined in terms of
 * TPS(Transactions per second). Rate of 5 would suggest, 5 transactions/second. Transaction could be a DB call, API call,
 * or a simple function call.
 * <p>
 * Every {@link RateLimiter} implementation should implement either {@link RateLimiter#throttle(Code)} or, {@link RateLimiter#entry()},
 * and {@link RateLimiter#exit()}. They can also choose to implement all.
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
     * <p>
     * {@link RateLimiter#exit()}
     */
    void entry();

    /**
     * Once we have started the execution after calling {@link RateLimiter#entry()}, this method must be
     * called to mark the end of the scope of rate limiter. In short, entry() and exit() represent the beginning and end of the
     * code that needs to be rate limited.
     *
     * @return true if the code is executed, false otherwise.
     */
    boolean exit();


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
