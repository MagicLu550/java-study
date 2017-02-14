package com.a.eye.by.design.singleton;

/**
 * 懒汉、线程不安全
 *
 */
public class Singleton {

    private static Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (null == instance) {
            instance = new Singleton();
        }
        return instance;
    }

}
