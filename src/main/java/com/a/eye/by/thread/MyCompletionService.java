package com.a.eye.by.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCompletionService implements Callable<String> {

    private int id;

    public MyCompletionService(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionService<String> completion = new ExecutorCompletionService<String>(executor);
        for (int i = 0; i < 10; i++) {
            completion.submit(new MyCompletionService(i));
        }
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completion.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

    public String call() throws Exception {
        System.out.println(this.id + " start");
        Thread.sleep(1000);
        System.out.println(this.id + " end");
        return this.id + " -----";
    }

}
