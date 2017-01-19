package com.a.eye.by.apm.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class UserClassFileTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if ("com.a.eye.by.apm.agent.User".equals(className)) {
            try {
                CtClass ctClass = ClassPool.getDefault().get(className.replace('/', '.'));

                CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello");

                ctMethod.insertBefore("System.out.println(\"before sayHello----\");");
                ctMethod.insertAfter("System.out.println(\"after sayHello----\");");

                return ctClass.toBytecode();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
