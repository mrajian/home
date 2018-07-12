package home.app.prepare.sort;

import java.util.Arrays;

/**
 * @author liangyongjian
 * @desc 基数排序
 * @create 2018-05-11 19:07
 **/
public class RadixSort {

    public static void main(String[] args) {
        int[] array = new int[]{2089, 326, 562, 88901, 58, 2100, 100001, 0, 109, 4, 89, 89260};

        if (array != null) {
            System.out.println("排序前：\n" + Arrays.toString(array));
        }

        array = radixSort(array, 0, getMaxDigitInArray(array));
        if (array != null) {
            System.out.println("排序后：\n" + Arrays.toString(array));
        }

    }

    private static int[] radixSort(int[] array, int digit, int maxDigit) {
        // 如果当前要入桶的基数处于的位数大于了最大的位数，则算法结束
        if (digit > maxDigit) {
            return array;
        }

        // 开始分配入桶，对当前位数的基数做计数排序
        // 计数排序的辅助数组长度为最大的基数，且因为每位上数的范围是 [0, 9]，
        // 所以这里长度给10，或者也可以算出最大的基数作为长度
        int[] count = new int[10];

        // 统计每个基数出现的次数
        for (int value : array) {
            count[getRadix(value, digit)]++;
        }

        // 进行累计 得出某个基数 小于等于该基数的基数个数
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] help = new int[array.length];
        // 从桶中收集 计数排序在获取排序结果时，需要逆序遍历 原数组 这样能保证算法的稳定性
        for (int i = array.length - 1; i >= 0; i--) {
            int radix = getRadix(array[i], digit);
            help[count[radix] - 1] = array[i];
            count[radix]--;
        }

        return radixSort(help, ++digit, maxDigit);
    }

    /**
     * 获取一个数组中最大元素的位数
     * @param array
     * @return
     */
    private static int getMaxDigitInArray(int[] array) {
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[index]) {
                index = i;
            }
        }
        return getMaxDigit(array[index]);
    }

    /**
     * 获取元素的最大位数
     * @param value
     * @return
     */
    private static int getMaxDigit(int value) {
        return String.valueOf(value).length();
    }


    /**
     * 获取 元素在 digit 位上的数值
     * @param value
     * @param digit
     * @return
     */
    private static int getRadix(int value, int digit) {
        return ((value / ((int)Math.pow(10, digit))) % 10);
    }

}
