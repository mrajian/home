package home.app.prepare.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * @author liangyongjian
 * @desc 洗牌算法
 * 算法的核心思想是使用Random 的 nextInt(int bound) 生成随机的[0, bound)范围的的整数
 * @create 2018-05-10 14:15
 **/
public class Shuffle {

    public static void main(String[] args) {
        int[] sequence = getSequence(1, 54);
        System.out.println("洗牌后的序列: \n" + Arrays.toString(sequence));
    }

    /**
     * 洗牌算法，指定序列的大小范围
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

        System.out.println("洗牌前的有序序列: \n" + Arrays.toString(sequence));

        Random random = new Random();
        for(int i = 0; i < length; i++) {
            int p = random.nextInt(length);
            int tmp = sequence[i];
            sequence[i] = sequence[p];
            sequence[p] = tmp;
        }
        return sequence;
    }

    /**
     * 洗牌算法，序列从0开始
     * @param maxNum
     * @return
     */
    public static int[] getSequence(int maxNum) {
        int[] sequence = new int[maxNum];
        for(int i = 0; i < maxNum; i++){
            sequence[i] = i;
        }

        Random random = new Random();
        for(int i = 0; i < maxNum; i++){
            int p = random.nextInt(maxNum);
            int tmp = sequence[i];
            sequence[i] = sequence[p];
            sequence[p] = tmp;
        }
        random = null;
        return sequence;
    }
}
