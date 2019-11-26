package com.yangfei.singlefunction.thread;

/**
 * <p>
 *  演示volatile非线程安全
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class AccountVol implements Runnable {
    static AccountVol instance = new AccountVol();
    static AccountVol instance1 = new AccountVol();
    static volatile int i = 0;

    public static void increase() {
        synchronized (instance){
            i ++;
        }

    }
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            i = 0;
            Thread t1 = new Thread(new AccountVol());
            Thread t2 = new Thread(new AccountVol());
            Thread t3 = new Thread(new AccountVol());
            t1.start();
            t2.start();
            t3.start();
            t1.join();
            t2.join();
            t3.join();
            if (i != 30000000) {
                System.out.println(i);
                break;
            }

        }


    }
}
