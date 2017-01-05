package com.a.eye.by.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCyclicBarrier {

    private static int[] timeWalk = { 5, 8, 15, 15, 10 };
    private static int[] timeSelf = { 1, 3, 4, 4, 5 };
    private static int[] timeBus = { 2, 4, 6, 6, 7 };

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Tour(barrier, timeWalk, "walk Tour"));
        executor.submit(new Tour(barrier, timeSelf, "self Tour"));
        executor.submit(new Tour(barrier, timeBus, "bus Tour"));
        executor.shutdown();
    }

    static String now() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date());
    }

    static class Tour implements Runnable {

        private int[] times;
        private CyclicBarrier cyclicBarrier;
        private String tourName;

        public Tour(CyclicBarrier barrier, int[] times, String tourName) {
            this.times = times;
            this.cyclicBarrier = barrier;
            this.tourName = tourName;
        }

        public void run() {
            try {
                Thread.sleep(times[0] * 1000);
                System.out.println(now() + " " + tourName + " Reached Shenzhen");
                cyclicBarrier.await();
                Thread.sleep(times[1] * 1000);
                System.out.println(now() + " " + tourName + " Reached guagnzhou");
                cyclicBarrier.await();
                Thread.sleep(times[2] * 1000);
                System.out.println(now() + " " + tourName + " Reached shanghai");
                cyclicBarrier.await();
                Thread.sleep(times[3] * 1000);
                System.out.println(now() + " " + tourName + " Reached sichuan");
                cyclicBarrier.await();
                Thread.sleep(times[4] * 1000);
                System.out.println(now() + " " + tourName + " Reached tonghua");
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

}
