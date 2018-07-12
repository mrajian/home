package home.app.prepare.sort;

import java.util.Arrays;

/**
 * @author liangyongjian
 * @desc 计数排序
 * @create 2018-05-04 11:54
 **/
public class CountingSort {

    public static void main(String[] args) {
        int[] array = new int[]{89, 32, 56, 80, 58, 21, 109, 46, 89, 60, 1};

        if (array != null) {
            System.out.println("排序前的序列: \n" + Arrays.toString(array));
        }

        int[] result1 = countingSort(array);
        if (result1 != null) {
            System.out.println("排序后的序列: \n" + Arrays.toString(array));
        }

        System.out.println();
        int[] result2 = countingSortWithOption(array);
        System.out.print("排序后： ");
        if (result2 != null) {
            System.out.println("排序后的序列: \n" + Arrays.toString(array));
        }
    }


    /**
     * 计数排序实现
     * @param arr
     * @return
     */
    public static int[] countingSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        // 找出待排序数组中的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 排序后的结果数组
        int[] result = new int[arr.length];

        // 存储统计数据的数组，大小为待排序数组的最大值 + 1
        int[] help = new int[max + 1];

        // 以下循环操作完成后，help[i] 保存着 arr 中，值为i的元素的总个数
        for (int i : arr) {
            help[i]++;
        }

        // 以下循环操作完成后，help[i] 保存着 arr 中，值小于或等于i的元素的总个数
        for (int i = 1; i < help.length; i++) {
            help[i] += help[i - 1];
        }

        // 填充目标数组： 针对arr[i] 这个元素，小于等于它的元素个数为 help[arr[i]]
        // 这样 arr[i] 这个元素应该放在 result 数组的第 help[arr[i]] 位置，
        // 即放到 result 数组下标为 help[arr[i]] - 1 的元素中
        // 为何要将 help[arr[i]] 做 -- 操作，这是为了兼容 arr[i] 重复的情况
        // 比如，arr中有两个3（arr[4] 和 arr[8]），可以认为第一个3比第二个3小，
        // 即如果小于等于第一个3的元素有5个，那小于等于第二个3的元素有6个，
        // 在 help数组中， help[3] = 6，因为是对arr做倒序循环，先处理arr[8]，help[arr[8]] = 6,
        // 将arr[8] 放到 result的第 6- 1 下标的元素后，因为小于等于第一个3（arr[4]）的元素有5个，
        // 所以此时需要将help[3]--，等待遍历到arr[4]的时候，根据help[3] = 5，将其放入result[5-1]下标的元素中
        for (int i = arr.length - 1; i > -1; i--) {
            result[help[arr[i]] - 1] = arr[i];
            help[arr[i]]--;
        }

        return result;

    }

    /**
     * 计数排序实现 对help数组的大小做下优化
     * @param arr
     * @return
     */
    public static int[] countingSortWithOption(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        // 找出待排序数组中的最大值 和 最小值
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }

        // 排序后的结果数组
        int[] result = new int[arr.length];

        // 存储统计数据的数组，大小为待排序数组的 [最大值 + 1 - 最小值]
        int[] help = new int[max + 1 - min];

        // 以下循环操作完成后，help[i - min] 保存着 arr 中，值为i的元素的总个数
        for (int i : arr) {
            help[i - min]++;
        }

        // 以下循环操作完成后，help[i - min] 保存着 arr 中，值小于或等于i的元素的总个数
        for (int i = 1; i < help.length; i++) {
            help[i] += help[i - 1];
        }

        // 注释类同上一个方法
        for (int i = arr.length - 1; i > -1; i--) {
            result[help[arr[i] - min] - 1] = arr[i];
            help[arr[i] - min]--;
        }

        return result;

    }

}
