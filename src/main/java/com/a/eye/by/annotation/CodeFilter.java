package com.a.eye.by.annotation;

@FilterDesc(name = "codeFilter", order = 1, type = FilterType.START)
public class CodeFilter extends BaseHandlerFilter {

    public CodeFilter(HandlerFilterManager handlerFilterManager) {
        super(handlerFilterManager);
    }

    public boolean doFilter() {
        System.out.println("codeFilter success");
        return true;
    }

}
