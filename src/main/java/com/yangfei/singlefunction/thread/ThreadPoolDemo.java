package com.yangfei.singlefunction.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <p>
 *  线程池
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class ThreadPoolDemo {
    public final static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN)) + "ThreadId:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        ExecutorService fixThreadPool = Executors.newFixedThreadPool(5);
        IntStream.rangeClosed(1, 10).forEach(n -> {
            //fixThreadPool.execute(task);
        });
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        IntStream.rangeClosed(1, 10).forEach(n -> {
            //立即执行该任务
            //scheduledThreadPool.execute(task);
            //指定时间之后调度任务
            //scheduledThreadPool.schedule(task, 10L, TimeUnit.SECONDS);
            //循环调度 -- 一个任务的开始时间为起点。之后的period时间，调度下一次任务，往复循环
            //scheduledThreadPool.scheduleAtFixedRate(task, 2L, 2L, TimeUnit.SECONDS);
            //上一个任务结束后，再经过delay时间后，进行下一次调度,往复执行
            scheduledThreadPool.scheduleWithFixedDelay(task, 2L, 2L, TimeUnit.SECONDS);
        });

    }
}
