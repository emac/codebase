/*
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 *
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
 */

package interview;

import java.util.Map;

/**
 * 统计控制台输入的一句话中不同字⺟字符出现的次数。例如：现有字符串"Hello World!"，上述字符串中各个
 * 字符的出现的次数为：
 * H:1
 * e:1
 * l:3
 * o:2
 * W:1
 * r:1
 * d:1
 * （不考虑数字、空格和特殊字符的个数，按照字符在字符串中出现的顺序显示。相同字母的大小写算两个不
 * 同字符）
 * 输入描述：
 * 控制台任意输入一段话，可以有空格和特殊符号
 * 输出描述：
 * 输出字符和字符对应的出现字数（字符和出现次数之间用:隔开，输出逻辑已经给出）
 * <p>
 * 示例1
 * 输入：
 * Hello World
 * 输出：
 * H:1
 * e:1
 * l:3
 * o:2
 * W:1
 * r:1
 * d:1
 *
 * @author Emac
 * @date 2023/9/4
 */
public class Za2302 {

    public static void main(String[] args) {
        System.out.println((int)'a'); // 97
        System.out.println((int)'z'); // 122
        System.out.println((int)'A'); // 65
        System.out.println((int)'Z'); // 90
        System.out.println(solution("Hello World"));
    }

    public static String solution(String sentence) {
        char[] alphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
                , 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        int[] counts = new int[52];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }

        int a = Character.getNumericValue('a');
        for (char c : sentence.toCharArray()) {
            if (Character.isLetter(c)) {
                // Character.getNumericValue不区分大小写字母
                int delta = Character.getNumericValue(c) - a;
                int offset = Character.isLowerCase(c) ? 0 : 26;
                counts[offset + delta] += 1;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                sb.append(alphabet[i]);
                sb.append(":");
                sb.append(counts[i]);
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    /* 其他解法-1
    * 作者：孙效颖
    * 思路：
    Map<Character,Integer> letterCountMap = new LinkedHashMap<>();
    for(int i=0;i<sentence.length();i++){
        char c = sentence.charAt(i);
        if(Character.isLetter(c)){
            letterCountMap.put(c,letterCountMap.getOrDefault(c,0)+1);
        }
    }
    for(Map.Entry<Character,Integer> entry : letterCountMap.entrySet()){
        System.out.println(entry.getKey()+":"+entry.getValue());
    }
    */
}
