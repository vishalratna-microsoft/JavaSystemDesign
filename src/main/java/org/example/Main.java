package org.example;

import org.example.ratelimiter.SimpleTokenBucketRateLimiter;
import org.example.ratelimiter.RateLimiter;

public class Main {
    public static void main(String[] args) {
        RateLimiter limiter = new SimpleTokenBucketRateLimiter(1);
        Thread[] group = new Thread[6];
        Runnable r = () -> {
            for (int i = 0; i < 100; i++) {
                int finalI = i;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
//                limiter.throttle(() -> System.out.println("Values:- " + Thread.currentThread().getName() + ": " + finalI));
                if (limiter.enter()) {
                    System.out.println("Values:- " + Thread.currentThread().getName() + ": " + finalI);
                }

            }
        };

        for (int i = 0; i < 6; i++) {
            group[i] = new Thread(r);
            group[i].start();
        }
    }
}