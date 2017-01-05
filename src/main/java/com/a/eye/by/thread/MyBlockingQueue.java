package com.a.eye.by.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MyBlockingQueue extends Thread {

    public static BlockingQueue<String> queue = new LinkedBlockingQueue<String>(1);

    private int index;

    public MyBlockingQueue(int index) {
        this.index = index;
    }

    public void run() {
        try {
            queue.put(String.valueOf(index));
            System.out.println(index + " in queue !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyBlockingQueue(i));
        }
        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        if (MyBlockingQueue.queue.isEmpty()) {
                            break;
                        }
                        String s = MyBlockingQueue.queue.take();
                        System.out.println(s + " is take !");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executor.execute(thread);
        executor.shutdown();
    }

}
