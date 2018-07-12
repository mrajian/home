package home.app.prepare.sort;

import java.util.Arrays;

/**
 * @author liangyongjian
 * @desc 快速排序
 * @create 2018-05-02 21:33
 **/
public class QuickSort {

    public static void main(String[] args) {

        // 以右边第一个元素为基准数 每次都和基准值交换
        int[] arrayA = new int[]{89, 32, 56, 80, 58, 21, 109, 46, 89, 60, 1};
        System.out.println("排序前：\n" + Arrays.toString(arrayA));
        sortRight(arrayA, 0, arrayA.length - 1);
        System.out.println("排序后：\n" + Arrays.toString(arrayA));


        // 以左边第一个元素为基准数 每次都和基准值交换
        int[] arrayB = new int[]{89, 32, 56, 80, 58, 21, 109, 46, 89, 60, 1};
        sortLeft(arrayB, 0, arrayB.length - 1);
        System.out.println("排序后：\n" + Arrays.toString(arrayB));

        // 以左边第一个元素为基准数 分别从右边和左边找到符合条件的值，让这两个值交换，走到最后的坐标和将基准值交换
        int[] arrayC = new int[]{89, 32, 56, 80, 58, 21, 109, 46, 89, 60, 1};
        quickSort(arrayC, 0, arrayC.length - 1);
        System.out.println("排序后：\n" + Arrays.toString(arrayC));

        // 挖坑+分治 以左边第一个元素为基准数 将从左边或右边找到的符合条件的值替换到坑中，走到最后的坐标替换为基准值
        int[] arrayD = new int[]{89, 32, 56, 80, 58, 21, 109, 46, 89, 60, 1};
        divideSort(arrayD, 0, arrayD.length - 1);
        System.out.println("排序后：\n" + Arrays.toString(arrayD));

        // 从中间取基准值的快排 依然是 挖坑+分治
        int[] arrayE = new int[]{89, 32, 58, 56, 58, 80, 21, 109, 46, 89, 60, 1};
        sortFromMiddle(arrayE, 0, arrayE.length - 1);
        System.out.println("排序后：\n" + Arrays.toString(arrayE));

        int[] arrayF = new int[]{89, 32, 58, 56, 58, 80, 21, 109, 46, 89, 60, 1};
        bubbleSort(arrayF);
        System.out.println("排序后：\n" + Arrays.toString(arrayF));


    }

    /**
     * 基准数在右侧 排序方法入口
     * @param array
     * @param start
     * @param end
     */
    public static void sortRight(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int baseIndex = sortWithRightStart(array, start, end);
        sortRight(array, start, baseIndex - 1);
        sortRight(array, baseIndex + 1, end);
    }

    /**
     * 以右边第一个元素为基准数
     * @param array
     * @param start
     * @param end
     */
    public static int sortWithRightStart(int[] array, int start, int end) {

        // 以右边第一个数为基准数，此时需要先从左边开始遍历
        int base = array[end];
        while(start < end) {
            // 先从左边开始遍历，找到第一个比基准数大的数
            while(start < end && array[start] <= base) {
                start++;
            }
            // 跳出了上面的循环，则说明两种情况 1、找到了比基准数大的数；OR 2、start == end
            if (start < end) {
                // 此时，start < end 这说明，的确找到了比基准数大的数，基准数位于end位置，交换
                array[end] = array[start];
                array[start] = base;

                // 此时，基准数放到了array[start]上，比基准数大的数放到了array[end]上，
                // end及其右边都是比基准值大的数，所以end需要向左走一步，
                // 而左边的start不需要移动，因为下面就要用到它所放置的基准值
                end--;
            }

            // 左边走完，再从右边开始遍历，找到第一个比基准数小的数据
            while (start < end && array[end] >= base) {
                end--;
            }
            // 跳出了上面的循环，则说明 1、找到了比基准数小的数据； OR 2、start==end
            if (start < end) {
                // 此时，start < end 这说明，的确找到了比基准数小的数，基准数位于start位置，交换
                // 此时 start放置的是基准值，需要与end交换
                array[start] = array[end];
                array[end] = base;

                // 此时，基准数放到了array[end]上，比基准数小的数放到了array[start]上，
                // start及其左边的数都是比基准值小的数，所以start需要向右走一步，
                // 而右边的end不需要移动，因为下面就要用到它所放置的基准值
                start++;
            }
        }
        // 这里返回start或end都可，因为start和end都是目前放置了基准值的下标
        return end;
    }


    /**
     * 基准数在左侧，排序方法入口
     * @param array
     * @param start
     * @param end
     */
    public static void sortLeft(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int baseIndex = sortWithLeftStart(array, start, end);
        sortLeft(array, start, baseIndex - 1);
        sortLeft(array, baseIndex + 1, end);
    }

    /**
     * 以左边第一个数为基准数
     * @param array
     * @param start
     * @param end
     */
    public static int sortWithLeftStart(int[] array, int start, int end) {
        // 左边第一个数为基准数，此时需要先从右边开始遍历
        int base = array[start];
        while(start < end) {
            // 先从右边开始遍历，找到第一个比基准数小的数
            while(start < end && array[end] >= base) {
                end--;
            }
            // 跳出了上面的循环，则说明两种情况 1、找到了比基准数小的数；OR 2、start == end
            if (start < end) {
                // 此时，start < end 这说明，的确找到了比基准数小的数，基准数位于start位置，交换
                array[start] = array[end];
                array[end] = base;

                // 此时，基准数放到了array[end]上，比基准数小的数放到了array[start]上，
                // start及其左边的数都是比基准值小的数，所以start需要向右走一步，
                // 而右边的end不需要移动，因为下面就要用到它所放置的基准值
                start++;
            }

            // 右边走完，再从左边开始遍历，找到第一个比基准数大的数据
            while(start < end && array[start] <= base) {
                start++;
            }
            // 跳出了上面的循环，则说明两种情况 1、找到了比基准数大的数；OR 2、start == end
            if (start < end) {
                array[end] = array[start];
                array[start] = base;

                // 此时，基准数放到了array[start]上，比基准数大的数放到了array[end]上，
                // end及其右边都是比基准值大的数，所以end需要向左走一步，
                // 而左边的start不需要移动，因为下面就要用到它所放置的基准值
                end--;
            }
        }
        // 这里返回start或end都可，因为start和end都是目前放置了基准值的下标
        return start;
    }


    /**
     * 区别上上面的两种方式：
     * 这种方式是，以左一为基准值，先从右边找小于基准值的数，再从左边找大于基准值的数，找到后，将这两者进行交换
     * 如果没有找到，则将基准值替换到一轮最后结束的位置的值
     * 这种方法，减少了置换的次数
     * @param array
     * @param start
     * @param end
     */
    static void quickSort(int[] array, int start, int end)  {
        if(start > end)
            return;

        int left, right, temp, base;
        base = array[start]; //temp中存的就是基准数
        left = start;
        right = end;
        while(left != right) {
            //顺序很重要，要先从右边开始找
            while(array[right] >= base && left < right)
                right--;
            //再找右边的
            while(array[left] <= base && left < right)
                left++;
            //交换两个数在数组中的位置
            if(left < right) {
                temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
        //最终将基准数归位
        array[start] = array[left];
        array[left] = base;

        quickSort(array, start, left-1);//继续处理左边的，这里是一个递归的过程
        quickSort(array, left+1, end);//继续处理右边的 ，这里是一个递归的过程
    }

    /**
     * 挖坑+分治法 快排
     * @param array
     * @param start
     * @param end
     */
    public static void divideSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start, right = end;
        // 以左侧第一个值为基准值 目前的坑是array[left]
        int base = array[left];
        while (left < right) {
            // 从右侧开始，找到小于基准值的数值
            while (left < right && array[right] >= base)
                right--;
            // 只有在 left < right 时才认为找到了小于基准值的数值
            if (left < right) {
                // 将小于基准值的数值放入坑中，且让left+1，下一步要从left+1开始向右侧查找
                // 此时 array[right]成为新的坑
                array[left++] = array[right];
            }

            // 从左侧开始，找到大于基准数的数值
            while (left < right && array[left] <= base)
                left++;
            // 只有在 left < right 时才认为找到了大于基准值的数值
            if (left < right) {
                array[right--] = array[left];
            }
        }
        // 跳出上面的while循环，只有一种可能 left == right
        if (left == right) {
            // 这时的坑为array[left]，将基准值填入
            array[left] = base;
        }
        // 此时基准值位于 array[left]位置，它的左侧小于等于它，它的右侧大于等于它
        // 对它的左侧和右侧区间做相同的处理
        divideSort(array, start, left - 1);
        divideSort(array, right + 1, end);
    }


    /**
     * 从中间取基准值的快排
     * @param arr
     * @param start
     * @param end
     */
    public static void sortFromMiddle(int arr[], int start, int end){
        int left = start, right = end;
        //找中间值
        int middleValue = arr[(start + end) / 2];
        int temp = 0;
        while(left < right) {
            // 从左侧开始找到大于基准值的数值
            while(arr[left] < middleValue)
                left++;

            // 从右侧开始找到小于基准值的数值
            while(arr[right] > middleValue)
                right--;

            if(left >= right)
                break;

            // 将左右两个值交换
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            //
            if(arr[left] == middleValue)
                --right;
            if(arr[right] == middleValue)
                ++left;
        }
        if(left == right) {
            left++;
            right--;
        }

        if(start < right)
            sortFromMiddle(arr, start, right);
        if(end > left)
            sortFromMiddle(arr, left, end);
    }

    public static void bubbleSort(int arr[]){
        for (int i = arr.length - 1; i > -1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }



}
