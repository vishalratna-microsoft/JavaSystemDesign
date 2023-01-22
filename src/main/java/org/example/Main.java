package org.example;

import org.example.ratelimiter.BurstySmoothRateLimiter;
import org.example.ratelimiter.RateLimiter;

public class Main {
    public static void main(String[] args) {
        RateLimiter limiter = new BurstySmoothRateLimiter(30,20);
        Thread[] group = new Thread[16];
        Runnable r = () -> {
            for (int i = 0; i < 50; i++) {
                if (limiter.acquire(10)) {
                    System.out.println("Values:- " + Thread.currentThread().getName() + ": " + i);
                }

            }
        };

        for (int i = 0; i < 16; i++) {
            group[i] = new Thread(r);
            group[i].start();
        }
    }
}