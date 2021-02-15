package com.company;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchSample {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 6;
        CountDownLatch countDownLatch = new CountDownLatch(8);
        for (int i = 0; i < threadCount; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000+new Random().nextInt(2000));
                    countDownLatch.countDown();
                    System.out.println("Поток "+w+" закончил свое выполнение");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        boolean result = countDownLatch.await(4, TimeUnit.SECONDS);
        if (result) {
            System.out.println("Все потоки завершили свою работу");
        } else {
            System.out.println("Не все потоки успели завершить свою работу");
        }
    }

}
