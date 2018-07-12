package home.app.prepare.sort;

import java.util.Arrays;

/**
 * @author liangyongjian
 * @desc 希尔排序
 * @create 2018-05-07 13:40
 **/
public class ShellSort {

    public static void main(String[] args) {
        int[] array = new int[]{89, 32, 56, 80, 58, 21, 0, 109, 46, 89, 60, 1};

        if (array != null) {
            System.out.println("排序前：\n" + Arrays.toString(array));
        }

        shellSort(array);
        if (array != null) {
            System.out.println("排序后：\n" + Arrays.toString(array));
        }
    }

    public static void shellSort(int[] arr){

        // 按照增量序列 划分子区间 逐步缩小增量 子区间也在逐步扩大
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {

            // 对子区间 跨组进行 进行 插入排序
            // 从每组的第二个元素开始，与插入排序思路一致，每组默认第一个元素是有序的
            for (int i = gap; i < arr.length; i++) {

                // 首先判断待排序的元素是否已经是有序序列中的最大的元素，如果是，不需要排序，直接放在原有位置
                if (arr[i] < arr[i - gap]) {
                    // 设置一个临时的下标变量 j
                    int j = i;
                    // 设置一个临时变量 temp  用于排序时移动变量
                    int temp = arr[j];

                    // 对一个子分区，如果 待排序的元素 小于 有序分区中最后一个元素，
                    // 则需要逆向遍历，为其找到合适位置 过程中需要移动有序分区中的元素，
                    // 提供一个可供待排序元素放入的坑
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 有序序列中的元素大于了待排序的元素，移动有序序列中的元素
                        // 这样 arr[j-gap] 成为一个新的坑
                        arr[j] = arr[j - gap];
                        // 让 下标变量 j 的值 与坑的下标同步
                        j -= gap;
                    }
                    // 跳出了循环，说明从有序序列中找到了小于待排序的元素，或者到了有序数组的最前端
                    // 把待排序的元素放入坑中，排序结束
                    arr[j] = temp;
                }
            }
        }
    }
}
