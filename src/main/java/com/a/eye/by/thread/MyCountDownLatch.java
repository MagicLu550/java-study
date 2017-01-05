package com.a.eye.by.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCountDownLatch {
    final static SimpleDateFormat sf = new SimpleDateFormat("yyyy-DD-mm HH:mm:ss");

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Worker worker1 = new Worker("zhangsan", 1000, countDownLatch);
        Worker worker2 = new Worker("lisi", 3000, countDownLatch);
        executor.execute(worker1);
        executor.execute(worker2);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all work done at " + sf.format(new Date()));
        executor.shutdown();
    }

    static class Worker extends Thread {
        private String workName;
        private int workTime;
        private CountDownLatch latch;

        public Worker(String workName, int workTime, CountDownLatch latch) {
            this.workName = workName;
            this.workTime = workTime;
            this.latch = latch;
        }

        public void run() {
            try {
                System.out.println("Worker:" + workName + " do work begin at " + sf.format(new Date()));
                Thread.sleep(workTime);
                System.out.println("Worker:" + workName + " do work end at " + sf.format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }

    }
}
