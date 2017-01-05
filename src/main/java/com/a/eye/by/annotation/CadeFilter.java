package com.a.eye.by.annotation;

@FilterDesc(name = "cadeFilter", order = 2, type = FilterType.START)
public class CadeFilter extends BaseHandlerFilter {

    public CadeFilter(HandlerFilterManager handlerFilterManager) {
        super(handlerFilterManager);
    }

    public boolean doFilter() {
        System.out.println("cadeFilter success");
        return true;
    }

}
