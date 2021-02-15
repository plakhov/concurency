package com.company;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 5; i++) {
            final int w = i;
            new Thread(() -> {
                System.out.println("Поток " + w + " остановился перед семафором");
                boolean result = false;
                try {
                    result = lock.tryLock(3, TimeUnit.SECONDS);
                    if (result) {
                        System.out.println("Поток " + w + " занял семафор");
                        Thread.sleep(2000);
                    } else {
                        System.out.println("Поток " + w + " не занял семафор");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (result) {
                        System.out.println("Поток " + w + " освободил cемафор");
                        lock.unlock();
                    }
                }
            }).start();
        }
    }
}
