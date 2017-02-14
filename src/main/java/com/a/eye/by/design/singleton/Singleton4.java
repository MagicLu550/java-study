package com.a.eye.by.design.singleton;

/**
 * 静态内部类
 *
 */
public class Singleton4 {

    private static class SingletonHolder {
        private static final Singleton4 singleton4 = new Singleton4();
    }

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        return SingletonHolder.singleton4;
    }
}
