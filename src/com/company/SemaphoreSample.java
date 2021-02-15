package com.company;

import java.util.concurrent.Semaphore;

public class SemaphoreSample {

    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(3);
        for (int i = 0; i < 5; i++) {
            final int w = i;
            new Thread(() -> {
                System.out.println("Поток " + w + " остановился перед семафором");
                try {
                    Thread.sleep(5000);
                    semaphore1.acquire(3);
                    Thread.sleep(5000);
                    System.out.println("Поток " + w + " занял семафор");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Поток " + w + " освободил cемафор");
                    semaphore1.release(3);
                }
            }).start();
        }
    }
}
