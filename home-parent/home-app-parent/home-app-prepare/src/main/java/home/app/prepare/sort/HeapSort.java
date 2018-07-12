package home.app.prepare.sort;

import java.util.Arrays;

/**
 * @author liangyongjian
 * @desc 堆排序
 * @create 2018-05-14 18:46
 **/
public class HeapSort {

    public static void main(String[] args) {
        int[] arrNeedAscOrder = new int[]{89, 32, 100, 56, 80, 58, 21, 99, 46, 0, 89, 60, 1, 29};
        if (arrNeedAscOrder != null) {
            System.out.println("排序前：\n" + Arrays.toString(arrNeedAscOrder));
        }
        // 初始化大顶堆
        buildMaxHeap(arrNeedAscOrder);
        if (arrNeedAscOrder != null) {
            System.out.println("大顶堆：\n" + Arrays.toString(arrNeedAscOrder));
        }
        // 升序排序
        heapSortByAsc(arrNeedAscOrder);

        if (arrNeedAscOrder != null) {
            System.out.println("升序排序后：\n" + Arrays.toString(arrNeedAscOrder));
        }


        int[] arrNeedDescOrder = new int[]{89, 32, 100, 56, 80, 58, 21, 99, 46, 0, 89, 60, 1, 29};
        if (arrNeedDescOrder != null) {
            System.out.println("排序前：\n" + Arrays.toString(arrNeedDescOrder));
        }
        // 初始化小顶堆
        buildMinHeap(arrNeedDescOrder);
        if (arrNeedDescOrder != null) {
            System.out.println("小顶堆：\n" + Arrays.toString(arrNeedDescOrder));
        }
        // 降序排序
        heapSortByDesc(arrNeedDescOrder);

        if (arrNeedDescOrder != null) {
            System.out.println("降序排序后：\n" + Arrays.toString(arrNeedDescOrder));
        }
    }

    /**
     * 升序排序
     * @param arr
     */
    public static void heapSortByAsc(int[] arr){

        // 将大顶推的根与最后一个元素交换
        for (int j = arr.length - 1; j > 0; j--) {
            // 将堆顶元素与末尾元素进行交换
            swap(arr, 0, j);
            // 然后再调整大顶推，注意此时堆的内容就要去掉已经有序的部分
            adjustMaxHeap(arr, 0, j);
        }
    }

    /**
     * 降序排序
     * @param arr
     */
    public static void heapSortByDesc(int[] arr){

        // 将大顶推的根与最后一个元素交换
        for (int j = arr.length - 1; j > 0; j--) {
            // 将堆顶元素与末尾元素进行交换
            swap(arr, 0, j);
            // 然后再调整大顶推，注意此时堆的内容就要去掉已经有序的部分
            adjustMinHeap(arr, 0, j);
        }
    }

    /**
     * 初始化大顶堆
     * @param arr
     */
    public static void buildMaxHeap(int[] arr) {
        // 最后一个非叶子节点计算公式
        int startIndex = arr.length / 2 - 1;
        // 初始化大顶堆，从最后一个非叶子节点开始，从下往上
        for (int i = startIndex; i >= 0; i--) {
            adjustMaxHeap(arr, i, arr.length);
        }
    }

    /**
     * 大顶堆调整过程
     * @param arr
     * @param index
     * @param length
     */
    public static void adjustMaxHeap(int[] arr, int index, int length) {
        int temp = arr[index];

        // 找到这个节点的两个子节点，父节点和最大的子节点比较，决定自己是否和子节点交换
        for (int subIndex = index * 2 + 1; subIndex < length; subIndex = subIndex * 2 + 1) {

            // subIndex = index * 2 + 1 是其中一个子节点，它肯定是存在的，不然它的父节点就决不会是父节点了，而是叶子节点
            // 另一个子节点为 subIndex + 1 用之前得先判断它是否存在
            if (subIndex + 1 < length && arr[subIndex] < arr[subIndex + 1]) {
                subIndex++;
            }
            // 将父节点与最大的子节点比较，看看是否需要调整二者的位置
            if (arr[subIndex] > temp) {
                // 既然最大的子节点大于了父节点，那么需要父节点与子节点交换，
                // 这里只是将子节点的值放到了父节点，父节点的值尚未交换到子节点，是因为此时子节点可能也是不稳定的，
                // 还需要继续与它的子节点比较，是否需要交换，所以，目前子节点的位置可当作一个坑
                arr[index] = arr[subIndex];
                // 这时 父节点的值调整到了 下标为subIndex的子节点上，那么也需要判断 子节点是否也需要向下调整
                // 于是，index 变为 subIndex 循环继续，取找subIndex的子节点
                index = subIndex;

            } else {
                break;
            }
        }
        // 最后，将坑填上
        arr[index] = temp;
    }

    /**
     * 初始化小顶堆
     * @param arr
     */
    public static void buildMinHeap(int[] arr) {
        // 最后一个非叶子节点计算公式
        int startIndex = arr.length / 2 - 1;
        // 初始化小顶堆，从最后一个非叶子节点开始，从下往上
        for (int i = startIndex; i >= 0; i--) {
            adjustMinHeap(arr, i, arr.length);
        }
    }

    /**
     * 小顶堆调整过程
     * @param arr
     * @param index
     * @param length
     */
    public static void adjustMinHeap(int[] arr, int index, int length) {
        int temp = arr[index];

        // 找到这个节点的两个子节点，父节点和最小的子节点比较，决定自己是否和子节点交换
        for (int subIndex = index * 2 + 1; subIndex < length; subIndex = subIndex * 2 + 1) {

            // subIndex = index * 2 + 1 是其中一个子节点，它肯定是存在的，不然它的父节点就决不会父节点了，而是叶子节点
            // 另一个子节点为 subIndex + 1 用之前得先判断它是否存在
            if (subIndex + 1 < length && arr[subIndex] > arr[subIndex + 1]) {
                subIndex++;
            }
            // 将父节点与最小的子节点比较，看看是否需要调整二者的位置
            if (arr[subIndex] < temp) {
                // 既然最小的子节点小于了父节点，那么需要父节点与子节点交换，
                // 这里只是将子节点的值放到了父节点，父节点的值尚未交换到子节点，是因为此时子节点可能也是不稳定的，
                // 还需要继续与它的子节点比较，是否需要交换，所以，目前子节点的位置可当作一个坑
                arr[index] = arr[subIndex];
                // 这时 父节点的值调整到了 下标为subIndex的子节点上，那么也需要判断 子节点是否也需要向下调整
                // 于是，index 变为 subIndex 循环继续，取找subIndex的子节点
                index = subIndex;

            } else {
                break;
            }
        }
        // 最后，将坑填上
        arr[index] = temp;
    }

    /**
     * 用位运算交换元素位置
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int arr[], int i, int j) {
        // 用位运算
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
