package com.a.eye.by.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MySemaphore extends Thread {

    private int id;
    private Semaphore position;

    public MySemaphore(int id, Semaphore position) {
        this.id = id;
        this.position = position;
    }

    public void run() {
        if (position.availablePermits() > 0) {
            System.out.println("顾客[" + this.id + "]进入房间，有空位");
        } else {
            System.out.println("顾客[" + this.id + "]进入房间，没空位，排队");
        }
        try {
            position.acquire();
            System.out.println("顾客[" + this.id + "]获得房间");
            Thread.sleep(1000);
            System.out.println("顾客[" + this.id + "]使用完毕");
            position.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 10; i++) {
            executor.execute(new MySemaphore(i, semaphore));
        }
        executor.shutdown();
        semaphore.acquireUninterruptibly(2);
        System.out.println("使用完毕，需要清扫了");
        semaphore.release(2);
    }

}
