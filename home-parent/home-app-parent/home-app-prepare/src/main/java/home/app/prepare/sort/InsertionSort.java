package home.app.prepare.sort;

import java.util.Arrays;

/**
 * @author liangyongjian
 * @desc 插入排序 分为直接插入排序 和 折半插入排序
 * @create 2018-05-04 18:22
 **/
public class InsertionSort {

    public static void main(String[] args) {
        int[] arrayA = new int[]{89, 32, 56, 80, 58, 21, 109, 46, 89, 60, 1};

        System.out.println("排序前：\n" + Arrays.toString(arrayA));

        insertionSort(arrayA);
        System.out.println("直接插入算法排序后：\n" + Arrays.toString(arrayA));

        int[] arrayB = new int[]{89, 32, 56, 80, 58, 21, 109, 46, 89, 60, 1};
        binaryInsertionSort(arrayB);
        System.out.println("折半插入算法排序后：\n" + Arrays.toString(arrayB));
    }

    /**
     * 直接插入排序
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int insertValue = 0;

        // 初始划分为两个序列，arr[0] 为一个有序数组，arr[1, length -1] 为无序数组
        // 实际上这两个序列都还在 arr 数组中，随着排序的进行 此消彼长
        // 从下标 1 开始，让 arr[i] 与有序序列比较，确定其在有序序列中的位置，放入有序序列中
        for (int i = 1; i < arr.length; i++) {

            // 首先判断待排序的元素是否已经是有序序列中的最大的元素，如果是，不需要排序，直接放在原有位置
            if (arr[i] < arr[i - 1]) {
                // insertValue为 临时变量，此时arr[i] 就可以当坑来用了，可以将其他元素移动到此
                insertValue = arr[i];

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

    /**
     * 折半插入排序
     * @param arr
     */
    public static void binaryInsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int insertValue = 0;
        // 初始划分为两个序列，arr[0] 为一个有序数组，arr[1, length -1] 为无序数组
        // 实际上这两个序列都还在 arr 数组中，随着排序的进行 此消彼长
        // 从下标 1 开始，让 arr[i] 与有序序列比较，用折半法确定其在有序序列中的位置，放入有序序列中
        for (int i = 1; i < arr.length; i++) {

            // 首先判断待排序的元素是否已经是有序序列中的最大的元素，如果是，不需要排序，直接放在原有位置
            if (arr[i] < arr[i - 1]) {
                // insertValue为 临时变量，此时arr[i] 就可以当坑来用了，可以将其他元素移动到此
                insertValue = arr[i];

                // 使用二分法，为value找到合适的位置下标
                int position = findPosition(arr, insertValue, 0, i - 1);

                // 移动元素
                for (int j = i; j > position; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[position] = insertValue;

            }

        }
    }

    /**
     * 使用二分法，为value找到合适的位置下标
     * @param arr
     * @param value
     * @param start
     * @param end
     */
    public static int findPosition(int[] arr, int value, int start, int end) {
        // 这里，已知value 是小于 arr[end]

        // 当start 等于 end 时 这个同一个位置，此时value必需要插入到此位置
        if (start == end) {
            return start;
        }

        while (start <= end) {
            // 找出中间的下标
            int mid = start + (end - start) / 2;

            if (arr[mid] > value) {
                // 中值大于 value 说明value应该位于左半区间，调整end下标的值
                end = mid - 1;
            } else if (arr[mid] < value) {
                // 中值小于 value 说明value应该位于右半区间，调整start下标的值
                start = mid + 1;
            } else {
                // 这时候是相等的关系，为了保证算法的稳定性，需要继续从右侧找
                start = mid + 1;
            }
        }
        // 为何最终会返回 end + 1 , 用实例推演下便知
        return end + 1;
    }



}
