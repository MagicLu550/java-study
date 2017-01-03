package com.a.eye.by.netty.callback;

public class MyFetcher implements Fetcher {

    private Data data;

    public MyFetcher(Data data) {
        this.data = data;
    }

    public void fetchData(FetcherCallback callback) {
        try {
            callback.onData(data);
        } catch (Exception e) {
            callback.onError(e.getCause());
        }
    }

}
