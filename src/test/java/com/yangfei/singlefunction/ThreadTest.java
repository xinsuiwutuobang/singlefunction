package com.yangfei.singlefunction;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class ThreadTest{
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private int localVar = 0;
    @Test
    public void lockFree() {
        System.out.println("localVar:" + localVar + " atomicInteger:" + atomicInteger.get());
        while (!atomicInteger.compareAndSet(localVar, localVar + 1)) {
            localVar = atomicInteger.get();
        }
        System.out.println("localVar:" + localVar + " atomicInteger:" + atomicInteger.get());
    }
    private static long t = 0;
    public static class ChangeT implements Runnable{
        private long to;
        public ChangeT(long to) {
            this.to = to;
        }
        @Override
        public void run() {
            while (true) {
                ThreadTest.t = to;
                Thread.yield();
                int addAndGet = atomicInteger.addAndGet(1);
                System.out.println("addAndGet:" + addAndGet);
            }
        }
    }
    public static class ReadT implements Runnable{

        @Override
        public void run() {
            while (true) {
                long temp = ThreadTest.t;
                if (temp != 111L && temp != -999L && temp != 333L && temp != -444L) {
                    System.out.println(temp);
                    Thread.yield();
                }
            }
        }
    }
    @Test
    public void atomicity(){
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();
    }

}
