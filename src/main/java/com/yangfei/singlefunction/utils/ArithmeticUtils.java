package com.yangfei.singlefunction.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 算法练习
 */
@Slf4j
public class ArithmeticUtils {
    public static void main(String[] args) {
        testBobbleSort();
    }

    /**
     * 二分查找
     * 又叫折半查找，要求待查找的序列有序。
     * 每次取中间位置的值与待查关键字比较，如果中间位置
     * 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
     * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字。
     * 如果找到关键字，则返回值为关键字在数组中的位置索引，且索引从0开始2
     * 如果没有找到关键字，返回值为负的插入点值，所谓插入点值就是第一个比关键字大的元素在数组中的位置索引，而且这个位置索引从1开始。
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = array[mid];
            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -1;  // key not found.
    }
    public static void testBinarySearch() {
        int[] array = {1, 2, 3, 4, 5, 6};
        //1、如果找到关键字，则返回值为关键字在数组中的位置索引，且索引从0开始
        // 2、如果没有找到关键字，返回值为负的插入点值，所谓插入点值就是第一个比关键字大的元素在数组中的位置索引，而且这个位置索引从1开始。
        int jdk = Arrays.binarySearch(array, -8);
        int my = binarySearch(array, -8);
        log.info(String.format("binary search the jdk result is: %d,my result is: %d",jdk,my));
    }
    /**
     * 冒泡排序
     * 比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
     * 这样对数组的第 0 个数据到 N-1 个数据进行一次遍历后，最大的一个数据就“沉”到数组第
     * N-1 个位置。
     * N=N-1，如果 N 不为 0 就重复前面二步，否则排序完成。
     * @param array 带排序数组
     * @Param n 数组长度
     */
    public static void bobbleSort(int[] array,int n) {
        //n次排序过程
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {
                ///前面的数字大于后面的数字就交换
                if (array[j - 1] > array[j]) {
                    int temp;
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void testBobbleSort() {
        int[] array = {1, 12, 3, 14, 5, 16};
        int[] jdk = array.clone();
        log.info(String.format("排序前：%s",Arrays.toString(array)));
        bobbleSort(array,array.length);
        log.info(String.format("排序后：%s",Arrays.toString(array)));

        log.info(String.format("jdk排序前：%s",Arrays.toString(array)));
        Arrays.sort(jdk);
        log.info(String.format("jdk排序后：%s",Arrays.toString(array)));
    }
}
