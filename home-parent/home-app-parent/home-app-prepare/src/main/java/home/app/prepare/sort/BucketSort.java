package home.app.prepare.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liangyongjian
 * @desc 桶排序
 * @create 2018-05-11 23:31
 **/
public class BucketSort {

    public static void sort(Integer[] arr) {
        int n = arr.length;

        /**
         * 创建链表（桶）集合并初始化，集合中的链表用于存放相应的元素
         */
        int bucketNum = 11; // 桶数
        List<LinkedList<Integer>> bucketList = new ArrayList<LinkedList<Integer>>();

        for(int i = 0; i < bucketNum; i++) {
            LinkedList<Integer> bucket = new LinkedList<Integer>();
            bucketList.add(bucket);
        }
        // 把元素放进相应的桶中
        for(int i = 0; i < n; i++){
            int index = (int) (arr[i] / bucketNum);
            bucketList.get(index).add(arr[i]);
        }
        // 对每个桶中的元素排序，并放进a中
        int index = 0;
        for (LinkedList<Integer> linkedList : bucketList) {
            int size = linkedList.size();
            if (size == 0) {
                continue;
            }
            /**
             * 把LinkedList<Integer>转化为Integer[]的原因是，之前已经实现了
             * 对数组进行排序的算法
             */
            int[] temp = new int[size];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = linkedList.get(i);
            }
            // 利用插入排序对temp排序
            insertionSort(temp);
            for (int i = 0; i < temp.length; i++) {
                arr[index] = temp[i];
                index++;
            }
        }

    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{89, 32, 100, 56, 80, 58, 21, 99, 46, 0, 89, 60, 1, 29};
        if (arr != null) {
            System.out.println("排序前：\n" + Arrays.toString(arr));
        }
        sort(arr);
        if (arr != null) {
            System.out.println("排序后：\n" + Arrays.toString(arr));
        }
    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 初始划分为两个序列，arr[0] 为一个有序数组，arr[1, length -1] 为无序数组
        // 实际上这两个序列都还在 arr 数组中，随着排序的进行 此消彼长
        // 从下标 1 开始，让 arr[i] 与有序序列比较，确定其在有序序列中的位置，放入有序序列中
        for (int i = 1; i < arr.length; i++) {

            // 首先判断待排序的元素是否已经是有序序列中的最大的元素，如果是，不需要排序，直接放在原有位置
            if (arr[i] < arr[i - 1]) {
                // insertValue为 临时变量，此时arr[i] 就可以当坑来用了，可以将其他元素移动到此
                int insertValue = arr[i];

                // index 为 当前有序数组的下标上限
                int index = i - 1;

                // 逆序遍历有序数据，如果 待排序数值 小于 有序数组的元素，移动有序数组的元素到 坑中
                // 第一个坑即为 arr[i] 位置，被移动的元素其位置变为新的坑
                // 逆序遍历直到找到小于 待排序数值的位置，或者遍历到首个元素（此时待排序数值就是最小的）
                while (index >= 0 && insertValue < arr[index]) {
                    arr[index + 1] = arr[index];
                    index--;
                }
                // 找到了合适的位置，这个位置必是一个坑（数值已经移动走了）将待排序的数值放入此坑中
                arr[index + 1] = insertValue;
            }

        }
    }

}
