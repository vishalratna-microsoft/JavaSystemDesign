package org.example;

import org.example.ratelimiter.TokenBucketRateLimiter;
import org.example.ratelimiter.RateLimiter;

public class Main {
    public static void main(String[] args) {
        RateLimiter limiter = new TokenBucketRateLimiter(2);
        Thread[] group = new Thread[6];
        Runnable r = () -> {
            for (int i = 0; i < 100; i++) {
                int finalI = i;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                limiter.throttle(() -> System.out.println("Values:- " + Thread.currentThread().getName() + ": " + finalI));
            }
        };

        for (int i = 0; i < 6; i++) {
            group[i] = new Thread(r);
            group[i].start();
        }
    }
}