package com.a.eye.by.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutor extends Thread {

    private int index;

    public MyExecutor(int index) {
        this.index = index;
    }

    public void run() {
        System.out.println("[" + this.index + "] start.....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[" + this.index + "] end.....");
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            service.execute(new MyExecutor(i));
        }
        service.shutdown();
    }

}
