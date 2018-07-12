package home.app.prepare.sort;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

/**
 * @author liangyongjian
 * @desc 位图排序
 * @create 2018-05-10 13:43
 **/
public class BitMapSort {

    private int ARRNUM = 800;
    int LEN_INT = 32;
    int mmax = 9999;
    int mmin = 1000;
    int N = mmax - mmin + 1;

    public static void main(String args[]) {
        // 自己实现的位图排序
        new BitMapSort().findDuplicate();


        // JDK 提供的位图
        new BitMapSort().findDupByJdk();
    }

    public void findDupByJdk() {
        System.out.println("*******调用JDK中的库方法--开始********");
        BitSet bitArray = new BitSet(N);
        int[] array = getArray(ARRNUM);
        for (int i = 0; i < ARRNUM; i++) {
            bitArray.set(array[i] - mmin);
        }
        int count = 0;
        for (int j = 0; j < bitArray.length(); j++) {
            if (bitArray.get(j)) {
                System.out.print(j + mmin + " ");
                count++;
            }
        }
        System.out.println();
        System.out.println("排序后的数组大小为：" + count );
        System.out.println("*******调用JDK中的库方法--结束********");
    }

    public void findDuplicate() {
        int[] array = this.getArray(ARRNUM);


        // 转换为位图数组
        int[] bitArray = this.setBit(array);
        printBitArray(bitArray);
    }

    public void printBitArray(int[] bitArray) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (getBit(bitArray, i) != 0) {
                count++;
                System.out.print(i + mmin + " ");
            }
        }
        System.out.println();
        System.out.println("去重排序后的数组大小为：" + count);
    }

    public int getBit(int[] bitArray, int k) {// 1右移 k % 32位 与上 数组下标为 k/32 位置的值
        return bitArray[k / LEN_INT] & (1 << (k % LEN_INT));
    }

    /**
     * 得出位图数组
     * @param array
     * @return
     */
    public int[] setBit(int[] array) {
        int length = array.length;
        // 确定位数组的长度，数组为整型，1个元素为4byte，即32bit，位图法可以放32个数
        int bitArrLength = N / LEN_INT + 1;
        int[] bitArray = new int[bitArrLength];
        for (int i = 0; i < length; i++) {
            int num = array[i] - mmin;
            // num / LEN_INT 确定 num 这个数使用位图表示时位于位图数组的下标
            // 位运算 按位或 |
            // 让 1 左移 n位 n=num 应该放的位置
            bitArray[num / LEN_INT] |= (1 << (num % LEN_INT));
        }
        return bitArray;
        
    }

    /**
     * 生成随机排序的数组
     * @param arrNum
     * @return
     */
    public int[] getArray(int arrNum) {

        int array[] = new int[arrNum];
        System.out.println("数组大小：" + arrNum);
        Random r = new Random();
        for (int i = 0; i < arrNum; i++) {
            // nextInt(N) 随机数 范围是 [0, N) 前闭后开
            // 这样，所有的元素都是从1000起步的，包括1000
            array[i] = r.nextInt(N) + mmin;
        }
        System.out.println("生成随机排列的数组: \n" + Arrays.toString(array));
        return array;
    }



}
