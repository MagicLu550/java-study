package com.a.eye.by.annotation;

public class HandlerTest {

    public static void main(String[] args) {

        HandlerFilterManager filterManager = new HandlerFilterManager();

        CadeFilter cadeFilter = new CadeFilter(filterManager);
        cadeFilter.add();
        CbdeFilter cbdeFilter = new CbdeFilter(filterManager);
        cbdeFilter.add();
        CodeFilter codeFilter = new CodeFilter(filterManager);
        codeFilter.add();

        filterManager.doFilter();
    }
}
