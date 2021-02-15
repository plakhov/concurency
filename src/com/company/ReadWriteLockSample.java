package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockSample {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Map<String, String> map = new HashMap<>();
        map.put("lock", "true");
        map.put("unlock", "false");
        Thread read1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                readWriteLock.readLock().lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(map.get("lock"));
//                readWriteLock.readLock().unlock();
            }
        });
        Thread read2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                readWriteLock.readLock().lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(map.get("unlock"));
//                readWriteLock.readLock().unlock();
            }
        });
        Thread write1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                readWriteLock.writeLock().lock();
                System.out.println(map.put("lock", "false"));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readWriteLock.writeLock().unlock();
            }
        });
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(read1);
        executorService.execute(read2);
        executorService.execute(write1);
        executorService.shutdown();
    }
}
