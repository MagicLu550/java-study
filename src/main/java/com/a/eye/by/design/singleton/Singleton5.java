package com.a.eye.by.design.singleton;

/**
 * 双重锁检测
 *
 */
public class Singleton5 {

    private static volatile Singleton5 singleton5;

    private Singleton5() {

    }

    public static Singleton5 getInstance() {
        if (null == singleton5) {
            synchronized (Singleton5.class) {
                if (null == singleton5) {
                    singleton5 = new Singleton5();
                }
            }
        }
        return singleton5;
    }
}
