package com.a.eye.by.apm.agent;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation inst) {
        System.out.println("Hi, I'm agent!");
        inst.addTransformer(new MyTransformer());
    }

}
