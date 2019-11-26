package com.yangfei.singlefunction.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class ReadWriteLock {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println(String.format("read value is：%s",value));
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock,int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            this.value = index;
            System.out.println(String.format("write value is：%s",value));
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLock demo = new ReadWriteLock();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleRead(readLock);
                    demo.handleRead(readLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleWrite(writeLock, new Random().nextInt());
                    demo.handleWrite(lock, new Random().nextInt());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        IntStream.rangeClosed(0,20).forEach(n -> {
            new Thread(readRunnable).start();
        });
        IntStream.rangeClosed(0,2).forEach(n -> {
            new Thread(writeRunnable).start();
        });
    }
}
