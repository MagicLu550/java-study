package com.a.eye.by.design.template;

public abstract class AbstractCalculate {

    public long cal(long a, long b) {
        System.out.println(a);
        System.out.println(b);
        return calculate(a, b);
    }

    abstract public long calculate(long a, long b);

}
