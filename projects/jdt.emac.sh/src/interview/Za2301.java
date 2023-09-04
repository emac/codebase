/*
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 *
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
 */

package interview;

/**
 * 小安有一块巧克力，上面有一排巧克力球，有些球上有坚果，有些球上没有坚果，小安想把巧克力掰成很多块，保证每次吃的时候每块上都至少有一个巧克力球，但有且只有一个坚果，小安想知道有多少种方式可以得到满足条件的若干巧克力块？
 * tips：
 * 1. 巧克力块大小可以不一样
 * 2. 只要有一个位置与另一种方式不一样，即表示两种方式不同
 * 3. 如果巧克力只包含一个坚果，只有一种方式，即不掰
 * <p>
 * 输入描述：
 * 两行，第一行一个数字N(1 <= N <= 100)，表示巧克力球的个数；
 * 第二行N个整数，每两个整数之间一个空格隔开。每个整数为0或者1，1表示这个巧克力球有坚果，0表示没有。
 * <p>
 * 输出描述：
 * 一个整数，表示方案数
 * <p>
 * 示例1
 * 输入：
 * 4
 * 1 0 1 1
 * 输出：
 * 2
 *
 * @author Emac
 * @date 2023/9/4
 */
public class Za2301 {

    public static void main(String[] args) {
        System.out.println(solution(0, new int[]{1, 0, 1, 1}));
        System.out.println(solution(0, new int[]{1, 1, 1, 1}));
        System.out.println(solution(0, new int[]{1, 0, 0, 1}));
        System.out.println(solution(0, new int[]{1, 0, 0, 0}));
    }

    public static int solution(int start, int[] occupies) {
        // 边界条件
        if (start > occupies.length - 1) {
            return 0;
        }

        // 1 找到第一个1
        // 1.1 跳过前置0
        int first = start;
        while (first <= occupies.length - 1 && occupies[first] == 0) {
            first++;
        }
        if (first == occupies.length) {
            // 没找到1
            return 0;
        } else if (first == occupies.length - 1) {
            // 最后1位是1
            return 1;
        }

        // 2 找到第二个1
        // 2.1 跳过前置0
        int next = first + 1;
        while (next <= occupies.length - 1 && occupies[next] == 0) {
            next++;
        }
        if (next == occupies.length) {
            // 没找到1，后面全0
            return 1;
        }

        // 3 递归
        int result = 0;
        for (int i = first + 1; i <= next; i++) {
            result += solution(i, occupies);
        }

        return result;
    }

    /* 其他解法-1（Bug: 未去除头部0）
    * 作者：张浩田
    * 思路：1与1之间得0的个数加一确定这一段的方案数，如10001方案数为4，1001方案数为3，101方案数为2，各个子段的方案数相乘即为总方案数。
    int n;
    int[] a;
    int ans=1,pre=a[0];
    List<Integer> s=new ArrayList<>();
    for(int i:a){
        if(pre==1){
            if(i==0){
                ans++;
            }else{
                s.add(ans);
                            ans=1;
                            pre=i;
            }
        }
    }
    ans=1;
    for(int a:s){
            ans=ans*a;
    }
    return ans;
    */
}
