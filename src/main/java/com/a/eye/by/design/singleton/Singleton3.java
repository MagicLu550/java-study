package com.a.eye.by.design.singleton;

/**
 * 饿汉模式，变种
 *
 */
public class Singleton3 {

    private static Singleton3 singleton3;

    static {
        singleton3 = new Singleton3();
    }

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        return singleton3;
    }
}
