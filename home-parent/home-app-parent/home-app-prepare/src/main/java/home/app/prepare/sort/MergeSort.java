package home.app.prepare.sort;

import java.util.Arrays;

/**
 * @author liangyongjian
 * @desc 归并排序
 * @create 2018-05-08 21:06
 **/
public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{89, 32, 56, 80, 58, 21, 0, 109, 46, 89, 60, 1};

        System.out.print("排序前： ");
        if (array != null) {
            System.out.println(Arrays.toString(array));
        }

        mergeSort(array, 0, array.length - 1);
        System.out.print("排序后： ");
        if (array != null) {
            System.out.println(Arrays.toString(array));
        }
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        // 创建一个新数组，用以存放 对两个子区间排序后的序列
        int[] tempArr = new int[end - start + 1];
        int index = 0;
        // 做合并与排序，从两个区间中的小的数值
        int i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                tempArr[index++] = arr[i++];
            } else {
                tempArr[index++] = arr[j++];
            }
        }

        // 把左边剩下的放入到tempArr中
        while (i <= mid) {
            tempArr[index++] = arr[i++];
        }

        // 把右边剩下的放入 tempArr 中
        while (j <= end) {
            tempArr[index++] = arr[j++];
        }

        // 将 有序的 tempArr 放入 arr 对应的位置中
        for (int k = 0; k < tempArr.length; k++) {
            arr[k + start] = tempArr[k];
        }
    }
}
