package zs.slg.dp;

import java.time.OffsetDateTime;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) return 0;
        return process(str.toCharArray(),0);
    }

    public static int process(char[] chars,int index) {
        if (index == chars.length) return 1;  // 到最后了  说明有解

        if (chars[index] == '0') return 0;  // 0 不能单转

        int ways = process(chars, index + 1);  // 单转
        if (index + 1 < chars.length && (chars[index] - '0') * 10 + (chars[index + 1] - '0') < 27){
            ways = process(chars, index + 2);  // 双转
        }
        return ways;
    }


    public static int dp(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length + 1];

        dp[chars.length] = 1;
        for (int index = chars.length - 1; index >= 0 ; index--) {
            if (chars[index] != '0'){
                int ways = dp[index + 1];
                if (index + 1 < chars.length && (chars[index] - '0') * 10 + (chars[index + 1] - '0') < 27) {
                    ways = dp[index + 2];
                }
                dp[index] = ways;
            }
        }
        return dp[0];
    }



    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp(s);

            if (ans0 != ans1) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
