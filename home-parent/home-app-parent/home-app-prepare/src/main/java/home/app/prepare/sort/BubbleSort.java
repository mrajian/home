package home.app.prepare.sort;

import java.util.Arrays;

/**
 * @author liangyongjian
 * @desc 冒泡排序
 * @create 2018-05-08 16:02
 **/
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = new int[]{32, 56, 80, 58, 21, 0, 46, 89, 60, 1, 89, 109, 110};
        System.out.println("排序后：\n" + Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        bubbleSort(array);
        System.out.println("排序后：\n" + Arrays.toString(array) + " 耗时：" + (System.currentTimeMillis() - startTime));

        int[] arrayA = new int[]{32, 56, 80, 58, 21, 0, 46, 89, 60, 1, 89, 109, 110};
        startTime = System.currentTimeMillis();
        bubbleSortWithOptimization1(arrayA);
        System.out.println("优化1排序后：\n" + Arrays.toString(arrayA) + " 耗时：" + (System.currentTimeMillis() - startTime));

        int[] arrayB = new int[]{32, 56, 80, 58, 21, 0, 46, 89, 60, 1, 89, 109, 110};
        bubbleSort(array);
        bubbleSortWithOptimization2(arrayB);
        System.out.println("优化2排序后：\n" + Arrays.toString(arrayB) + " 耗时：" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 最原始的冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 将较大的数值逐渐往后挪，这才是冒泡，每一趟做完后，最大的值都挪到了最后
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 优化后的冒泡排序
     * 某次冒泡中，如果一次交换都没有发生，则可以认为序列已经有序
     * @param arr
     */
    public static void bubbleSortWithOptimization1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成
            boolean flag = true;
            // 将较大的数值逐渐往后挪，这才是冒泡，每一趟做完后，最大的值都挪到了最后
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * 再次优化冒泡排序
     * 1、某次冒泡中，如果一次交换都没有发生，则可以认为序列已经有序
     * 2、某次冒泡中，记录最后一次发生交换操作的下标，既然是最后一次交换，这说明此下标后的元素都已经有序，无需再遍历
     * @param arr
     */
    public static void bubbleSortWithOptimization2(int[] arr) {
        // 优化2：记录最后一次发生交换操作的下标，既然是最后一次交换，这说明此下标后的元素都已经有序，无需再遍历
        int lastReplaceIndex = arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {

            // 优化1：设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成
            boolean flag = true;

            // 记录发生交换操作的下标
            int index = 0;

            // 将较大的数值逐渐往后挪，这才是冒泡，每一趟做完后，最大的值都挪到了最后
            for (int j = 0; j < lastReplaceIndex; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                    index = j;
                }
            }
            if (flag) {
                break;
            }

            lastReplaceIndex = index;
        }
    }

    // 这个方法是我最初写得，但它不是冒泡，我写错了，是类似于简单选择排序，每一趟都是将小的值放在了前面
    // 还不如简单选择排序，因为简单选择排序 记录小值的下标，在一趟遍历结束后才会交换
    public static void bubbleSort1(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

    }

}
