package com.a.eye.by.apm.agent;

import com.google.common.util.concurrent.RateLimiter;

public class MyRateLimiter {

    public static void main() {

    }

    public static void limit() {
        RateLimiter limter = RateLimiter.create(1000);
        if (limter.tryAcquire()) {
            System.out.println("accept the request");
        } else {
            System.out.println("refuse the request");
        }
    }

}
