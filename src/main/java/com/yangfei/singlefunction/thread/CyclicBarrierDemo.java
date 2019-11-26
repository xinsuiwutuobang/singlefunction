package com.yangfei.singlefunction.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>
 *  循环栏珊测试
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class CyclicBarrierDemo {
    //士兵多线程，循环栏珊通过构造传递进去
    public static class Solider implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclic;

        Solider(CyclicBarrier cyclic, String soliderName) {
            this.cyclic = cyclic;
            this.soldier = soliderName;
        }
        @Override
        public void run() {
            try {
                cyclic.await();
                doWork(soldier);
                cyclic.await();
                doWork(soldier);
                cyclic.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        void doWork(String soliderName) {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
                System.out.println("线程run：" + soliderName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：【士兵" + N + "个任务完成！】");
                //flag = false;
            } else {
                System.out.println("司令：【士兵" + N + "个集合完成！】");
                flag = true;
            }
        }

        public static void main(String[] args) {
            final int N = 10;
            Thread[] allSoldier = new Thread[N];
            boolean flag = false;
            CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
            System.out.println("集合队伍");
            for (int i = 0; i < 1; i++) {
                System.out.println("士兵" + i + "报道");
                allSoldier[i] = new Thread(new Solider(cyclic, "士兵" + i));
                allSoldier[i].start();
            }
        }
    }
}
