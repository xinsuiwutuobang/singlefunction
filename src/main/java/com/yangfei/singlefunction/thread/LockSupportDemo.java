package com.yangfei.singlefunction.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 *
 * </p>
 * 线程阻塞工具LockSupportDemo
 * @author yangfei
 * @since 2019-05-18
 */
public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                System.out.println("in " + getName() + "park");
            }
        }

        public static void main(String[] args) throws InterruptedException {
            LockSupport.unpark(t1);
            LockSupport.unpark(t2);
            t1.start();
            Thread.sleep(100);
            t2.start();
            LockSupport.unpark(t1);
            LockSupport.unpark(t2);
            t1.join();
            t2.join();
        }
    }
}
