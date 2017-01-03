package com.a.eye.by.netty.callback;

public class Worker {

    public static void main(String[] args) {
        MyFetcher myFetcher = new MyFetcher(new Data(2, 2));
        myFetcher.fetchData(new FetcherCallback() {

            public void onData(Data data) throws Exception {
                System.out.println("Data received: " + data);
            }

            public void onError(Throwable cause) {
                System.out.println("An error accour: " + cause.getMessage());
            }

        });
    }
}
