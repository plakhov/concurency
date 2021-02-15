package com.company;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierSample {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(4);
        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(3);
        Thread mainThread = Thread.currentThread();
        for (int i = 0; i < 4; i++) {
            final int w = i;
            Thread thread = new Thread(() -> {
                try {
                    System.out.println("Состояние основного потока:"+mainThread.getState());
                    System.out.println("Поток " + w + " начинает подготовку");
                    Thread.sleep(1000 + new Random().nextInt(2000));
                    System.out.println("Поток " + w + " готов");
                    cyclicBarrier1.await();
                    System.out.println("Поток " + w + " долждался остальных");
                    Thread.sleep(1000 + new Random().nextInt(2000));
                    cyclicBarrier2.await();
                    System.out.println("Поток " + w + " опять долждался остальных");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

            });
//            thread.setDaemon(true);
            thread.start();
        }
        return;
    }

}
