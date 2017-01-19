package com.a.eye.by.apm.agent;

import java.lang.instrument.Instrumentation;

public class UserAgent {

    public void premain(String args, Instrumentation inst) {
        inst.addTransformer(new UserClassFileTransformer());
    }

    public static void main(String[] args) {
        User user = new User();
        user.sayHello();
    }

}
