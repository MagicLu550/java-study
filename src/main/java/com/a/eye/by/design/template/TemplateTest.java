package com.a.eye.by.design.template;

public class TemplateTest {

    public static void main(String[] args) {
        long a = 10;
        long b = 5;
        AbstractCalculate cal = new PlusCalculate();
        System.out.println(cal.cal(a, b));
        cal = new MinusCalculate();
        System.out.println(cal.cal(a, b));
    }

}
