package com.yangfei.singlefunction.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class TryLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + " :my job done lock1");
                                return;
                            } finally {
                                lock2.unlock();
                            }
                        }

                    } finally {
                        lock1.unlock();
                    }

                }
            }
        }else{
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(800L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + " :my job done lock2");
                                return;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TryLock lock1 = new TryLock(1);
        TryLock lock2 = new TryLock(2);
        Thread thread1 = new Thread(lock1);
        Thread thread2 = new Thread(lock2);
        thread1.start();
        thread2.start();
    }
}



