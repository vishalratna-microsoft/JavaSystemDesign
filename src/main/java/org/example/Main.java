package org.example;

import org.example.graphs.algorithms.CountIslandsInMatrix;
import org.example.ratelimiter.BurstySmoothRateLimiter;
import org.example.ratelimiter.RateLimiter;

public class Main {
    //    public static void main(String[] args) {
//        RateLimiter limiter = new BurstySmoothRateLimiter(10,10);
//        Thread[] group = new Thread[16];
//        Runnable r = () -> {
//            for (int i = 0; i < 50; i++) {
//                if (limiter.acquire(1)) {
//                    System.out.println("Values:- " + Thread.currentThread().getName() + ": " + i);
//                }
//
//            }
//        };
//
//        for (int i = 0; i < 16; i++) {
//            group[i] = new Thread(r);
//            group[i].start();
//        }
//    }
}