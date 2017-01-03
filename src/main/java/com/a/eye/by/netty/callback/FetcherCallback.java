package com.a.eye.by.netty.callback;

public interface FetcherCallback {
    
    void onData(Data data) throws Exception;

    void onError(Throwable cause);
}
