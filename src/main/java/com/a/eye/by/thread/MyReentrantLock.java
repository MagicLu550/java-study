package com.a.eye.by.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock extends Thread {

    private int i;

    TestReentrantLock lock;

    public MyReentrantLock(int i, TestReentrantLock lock) {
        this.i = i;
        this.lock = lock;
    }

    public void run() {
        lock.print(i);
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        TestReentrantLock lock = new TestReentrantLock();
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyReentrantLock(i, lock));
        }
        executor.shutdown();
    }

}

class TestReentrantLock {
    private ReentrantLock lock = new ReentrantLock();

    public void print(int i) {
        lock.lock();
        System.out.println(i + "已经获取到了控制权");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(i + "释放了控制权");
            lock.unlock();
        }
    }
}
