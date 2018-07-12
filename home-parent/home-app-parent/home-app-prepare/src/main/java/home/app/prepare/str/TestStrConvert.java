package home.app.prepare.str;

/**
 * @author liangyongjian
 * @desc 测试字符串反转 三步走算法
 * @create 2018-04-25 23:05
 **/
public class TestStrConvert {

    /**
     * 找到空白字符串的下标
     * @param charArray
     * @return
     */
    public static String findSpaceStrIndex(char[] charArray) {
        if (charArray == null || charArray.length == 0) return null;

        char space = ' ';
        String indexStr = "";
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == space) {
                indexStr += i + ",";
            }
        }
        if (indexStr.equals("")) return null;
        return indexStr;
    }

    /**
     * 字符串翻转，指定区间
     * @param charArray
     * @param start
     * @param end
     */
    public static void reverseSubStr(char[] charArray, int start, int end) {
        if (charArray == null || charArray.length == 0) return;

        if (charArray.length < end || charArray.length < start) return;

        if (start >= end) return;

        if (start < 0 || end < 0) return;

        char temp;
        while (start < end) {
            temp = charArray[start];
            charArray[start++] = charArray[end];
            charArray[end--] = temp;
        }
    }

    /**
     * 字符串翻转 入口方法
     * @param str
     * @return
     */
    public static String reverseStr(String str) {
        char[] charArray = str.toCharArray();
        String indexStr = findSpaceStrIndex(charArray);

        if (indexStr == null || indexStr.equals("")) return null;

        String[] indexArray = indexStr.split(",");

        for (int i = 0; i <= indexArray.length; i++) {
            if (i == 0) {
                reverseSubStr(charArray, 0, Integer.parseInt(indexArray[i]) - 1);
            } else if (i < indexArray.length) {
                reverseSubStr(charArray,
                        Integer.parseInt(indexArray[i - 1]) + 1,
                        Integer.parseInt(indexArray[i]) - 1);
            } else if (i == indexArray.length) {
                if (Integer.parseInt(indexArray[i-1]) != charArray.length - 1) {
                    reverseSubStr(charArray,
                            Integer.parseInt(indexArray[i - 1]) + 1,
                            charArray.length - 1);
                }
            }
        }

        reverseSubStr(charArray, 0, charArray.length - 1);
        return String.valueOf(charArray);
    }

    public static void main(String[] args) {

        System.out.println(reverseStr("I am a student. I want a lot of money!"));
    }


}
