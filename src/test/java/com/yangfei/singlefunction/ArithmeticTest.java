package com.yangfei.singlefunction;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@Slf4j
public class ArithmeticTest extends BaseTest {
    /**
     * 二分查找
     * 又叫折半查找，要求待查找的序列有序。
     * 每次取中间位置的值与待查关键字比较，如果中间位置
     * 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
     * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字。
     * 如果找到关键字，则返回值为关键字在数组中的位置索引，且索引从0开始2
     * 如果没有找到关键字，返回值为负的插入点值，所谓插入点值就是第一个比关键字大的元素在数组中的位置索引，而且这个位置索引从1开始。
     *
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

    @Test
    public void testBinarySearch() {
        int[] array = {1, 2, 3, 4, 5, 6};
        //1、如果找到关键字，则返回值为关键字在数组中的位置索引，且索引从0开始
        // 2、如果没有找到关键字，返回值为负的插入点值，所谓插入点值就是第一个比关键字大的元素在数组中的位置索引，而且这个位置索引从1开始。
        int jdk = Arrays.binarySearch(array, -8);
        int my = binarySearch(array, -8);
        log.info(String.format("binary search the jdk result is: %d,my result is: %d", jdk, my));
    }

    /**
     * 冒泡排序
     * 比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
     * 这样对数组的第 0 个数据到 N-1 个数据进行一次遍历后，最大的一个数据就“沉”到数组第
     * N-1 个位置。
     * N=N-1，如果 N 不为 0 就重复前面二步，否则排序完成。
     *
     * @param array 带排序数组
     * @Param n 数组长度
     */
    public static void bobbleSort(int[] array, int n) {
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

    @Test
    public void testBobbleSort() {
        int[] array = {5, 4, 3, 2, 1};
        int[] jdk = array.clone();
        log.info(String.format("排序前：%s", Arrays.toString(array)));
        bobbleSort(array, array.length);
        log.info(String.format("排序后：%s", Arrays.toString(array)));
        log.info(String.format("jdk快速排序前：%s", Arrays.toString(jdk)));
        Arrays.sort(jdk);
        log.info(String.format("jdk快速排序后：%s", Arrays.toString(jdk)));
    }

    /**
     * 插入排序
     * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应的位置并插入。
     * 插入排序非常类似于整扑克牌。在开始摸牌时，左手是空的，牌面朝下放在桌上。接着，一次从
     * 桌上摸起一张牌，并将它插入到左手一把牌中的正确位置上。为了找到这张牌的正确位置，要将
     * 它与手中已有的牌从右到左地进行比较。无论什么时候，左手中的牌都是排好序的。
     * 如果输入数组已经是排好序的话，插入排序出现最佳情况，其运行时间是输入规模的一个线性函
     * 数。如果输入数组是逆序排列的，将出现最坏情况。平均情况与最坏情况一样，其时间代价是(n2)。
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            //插入的值
            int insertValue = array[i];
            //被插入的位置
            int index = i - 1;
            //如果插入的值比数组从后向前方向插入的位置前的数小，
            while (index >= 0 && insertValue < array[index]) {
                //array index 向后移动
                array[index + 1] = array[index];
                //index 向前移动
                index--;
            }
            //插入的值放入合适位置，前边做了index -- 找到合适的位置，所以这里直接index + 1
            array[index + 1] = insertValue;
        }
    }
    @Test
    public void insertSortTest() {
        int[] array = {5, 4, 3, 2, 1};
        insertSort(array);
        log.info(String.format("排序前：%s", Arrays.toString(array)));
        log.info(String.format("排序后：%s", Arrays.toString(array)));
    }

    /**
     * 快速排序
     * 基于分治的思想，是冒泡排序的改进型。选择一个关键值作为基准值。比基准值小的都在左边序列（一般是无序的），
     * 比基准值大的都在右边（一般是无序的）。一般选择序列的第一个元素。
     * 一次循环：从后往前比较，用基准值和最后一个值比较，如果比基准值小的交换位置，如果没有
     * 继续比较下一个，直到找到第一个比基准值小的值才交换。找到这个值之后，又从前往后开始比
     * 较，如果有比基准值大的，交换位置，如果没有继续比较下一个，直到找到第一个比基准值大的
     * 值才交换。直到从前往后的比较索引>从后往前比较的索引，结束第一次循环，此时，对于基准值
     * 来说，左右两边就是有序的了。
     *
     * @param array
     */
    public static void quickSort(int[] array,int low,int hight) {
        int start = low;
        int end = hight;
        int key = array[low];
        //start != end == 中间没排好序，两边已经排好，start == end 完成一次排序，key两边分别小于大于key
        while (start < end) {
            //从后往前比较，直到找到小于k的元素，交换两者位置，否则继续从后往前比较
            while (start < end && array[end] >= key) {
                end --;
            }
            if (array[end] <= key) {
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }
            //从前往后比较，直到找到大于k的元素，交换两者位置，否则继续从前往后比较
            while (start < end && array[start] <= key ) {
                start ++;
            }
            if (array[start] >= key) {
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }
        }
        //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的
        //值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        if (start > low) {
            quickSort(array, low, start - 1);
        }
        if (end < hight) {
            quickSort(array,end + 1,hight);
        }
    }
    @Test
    public void quickSortTest() {
        int[] jdk = baseArray.clone();
        log.info(String.format("排序前：%s", Arrays.toString(baseArray)));
        quickSort(baseArray, 0, baseArray.length - 1);
        log.info(String.format("排序后：%s", Arrays.toString(baseArray)));
        log.info(String.format("jdk排序前：%s", Arrays.toString(jdk)));
        Arrays.sort(jdk);
        log.info(String.format("jdk排序后：%s", Arrays.toString(jdk)));
    }

    /**
     * 希尔排序
     * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列
     * 中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
     * 1.选择一个增量序列t1,t2,...tk,其中ti > tj,tk = 1;
     *
     * @param array
     */
    public void shellSort(int[] array) {

    }
}
