package home.app.prepare.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author liangyongjian
 * @desc 简单选择排序
 * @create 2018-05-07 19:17
 **/
public class SelectionSort {

    public static void main(String[] args) {
        int[] array = new int[]{89, 32, 56, 80, 58, 21, 0, 109, 46, 89, 60, 1};

        if (array != null) {
            System.out.println("排序前：\n" + Arrays.toString(array));
        }

        selectionSort(array);
        if (array != null) {
            System.out.println("排序后：\n" + Arrays.toString(array));
        }


        array = getSequence(1, 54);
        System.out.println("经过洗牌算法后的无重复序列: \n" + Arrays.toString(array));
        selectionSort(array);
        System.out.println("经过简单选择排序后的无重复序列: \n" + Arrays.toString(array));
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // k 用于记录遍历过程中 比较小的数值的小标
            // 一趟遍历完成后 k 的值就是待排序序列中最小数值的下标
            int k = i;
            for (int j = k + 1; j < arr.length; j++) {
                if (arr[k] > arr[j]) {
                    k = j;
                }
            }

            // 当 arr[i] 大于 arr[k] 时，说明从待排序序列中找到了比arr[i]小的数值，需要交换
            if (arr[i] > arr[k]) {
                int min = arr[k];
                arr[k] = arr[i];
                arr[i] = min;
            }
        }
    }

    /**
     * 生成随机的序列，不重复，可用于洗牌算法
     * @param startNum
     * @param maxNum
     * @return
     */
    public static int[] getSequence(int startNum, int maxNum) {
        int length = maxNum - startNum + 1;
        int[] sequence = new int[length];
        for(int i = 0; i < length; i++){
            sequence[i] = i + startNum;
        }

        System.out.println("正序排列的无重复序列: \n" + Arrays.toString(sequence));

        Random random = new Random();
        for(int i = 0; i < length; i++) {
            int p = random.nextInt(length);
            int tmp = sequence[i];
            sequence[i] = sequence[p];
            sequence[p] = tmp;
        }
        return sequence;
    }

}
