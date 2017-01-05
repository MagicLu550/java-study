package com.a.eye.by.annotation;

public abstract class BaseHandlerFilter implements IHandlerFilter {

    private HandlerFilterManager handlerFilterManager;

    public BaseHandlerFilter(HandlerFilterManager handlerFilterManager) {
        this.handlerFilterManager = handlerFilterManager;
    }

    public void add() {
        handlerFilterManager.addFilter(this);
    }

}
