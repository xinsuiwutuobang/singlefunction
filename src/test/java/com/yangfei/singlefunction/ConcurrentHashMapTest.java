package com.yangfei.singlefunction;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * <p>
 * 虽然 `ConcurrentHashMap` 的方法都线程安全，但是对同一个 Key 调用多个方法会引发竞态条件，
 * 对不同的 key 递归调用同一个方法会导致死锁。
 *
 * 使用 `ConcurrentHashMap` 可以很方便地实现线程安全，
 * 可以根据需要挑选合适的方法并且每个 Key 只使用一次。
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class ConcurrentHashMapTest extends BaseTest {
    private final ConcurrentHashMap<Integer,Integer>  map = new ConcurrentHashMap<Integer,Integer>();
    public void update() {
        Integer result = map.get(1);
        if (result == null) {
            map.put(1, 1);
        }
        else {
            map.put(1, result + 1);
        }
    }
    public void update1(){
        map.compute(1, (key, value) -> {
            if (value == null) {
                return 1;
            } else {
                return value + 1;
            }
        });
    }

    /**
     * 问题在于两个线程先调用 `get` 接着调用 `put`，因此看到的都是空值，
     * 把值更新为1而不是预期的
     * 解决方法，只使用 `compute` 一个方法完成更新
     * java.lang.AssertionError:
     * Expected :2
     * Actual   :1
     * @throws InterruptedException
     */
    @Test
    public void testUpdate() throws InterruptedException    {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> { update(); });
        executor.execute(() -> { update(); });
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        assertEquals(2 , map.get(1).intValue());
    }

    public void update12() {
        map.compute(1, (key,value) -> {
            map.compute(2, (k, v) -> { return 2; });
            return 2;
        });
    }
    public void update21() {
        map.compute(2, (key,value) -> {
            map.compute(1, (k, v) -> { return 2; });
            return 2;
        });
    }

    /**
     * 不同的 Key 在 `compute` 方法内部再次调用 `compute` 方法。
     * `update12` 先处理 Key 1 然后是 Key 2，
     * `update21` 先处理 Key 2 然后是 Key 1。运行测试，会看到死锁
     *
     * 为什么会发生死锁，必须理解 `ConcurrentHashMap` 的内部结构。
     * ConcurrentHashMap` 使用数组来存储 Key/Value 映射。每次更新映射时，
     * 都会锁定存储映射的数组。因此，在上面的测试中，
     * 调用 `compute` 计算 Key 1 时锁定了 Key 1 的数组。然后尝试为 Key 2 锁定数组元素，
     * 但这时已被另一个线程锁定，调用 `compute` 计算 Key 2 并尝试锁定 Key 1 数组元素，
     * 发生死锁。
     * 只在需要更新时才对数组元素进行锁定，像 `get` 这样的只读方法不会加锁。
     * 因此，在 `compute` 方法中使用 `get` 方法没有问题。
     * @throws InterruptedException
     */
    @Test
    public void testUpdateRecursive () throws InterruptedException {
        map.put(1, 1);
        map.put(2, 2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> { update12(); });
        executor.execute(() -> { update21(); });
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
    }
}
