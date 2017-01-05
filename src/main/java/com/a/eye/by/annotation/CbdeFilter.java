package com.a.eye.by.annotation;

@FilterDesc(name = "cbdeFilter", order = 3, type = FilterType.START)
public class CbdeFilter extends BaseHandlerFilter {

    public CbdeFilter(HandlerFilterManager handlerFilterManager) {
        super(handlerFilterManager);
    }

    public boolean doFilter() {
        System.out.println("cbdeFilter success");
        return false;
    }

}
