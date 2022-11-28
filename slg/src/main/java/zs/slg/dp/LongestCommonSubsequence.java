package zs.slg.dp;

/**
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 */
public class LongestCommonSubsequence {

    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) return 0;
        return process(s1.toCharArray(), s2.toCharArray(), s1.length() - 1, s2.length() - 1);
    }

    // str1[0...i]和str2[0...j]，这个范围上最长公共子序列长度是多少？
    // 可能性分类:
    // a) 最长公共子序列，一定不以str1[i]字符结尾、也一定不以str2[j]字符结尾
    // b) 最长公共子序列，可能以str1[i]字符结尾、但是一定不以str2[j]字符结尾
    // c) 最长公共子序列，一定不以str1[i]字符结尾、但是可能以str2[j]字符结尾
    // d) 最长公共子序列，必须以str1[i]字符结尾、也必须以str2[j]字符结尾
    // 注意：a)、b)、c)、d)并不是完全互斥的，他们可能会有重叠的情况
    // 但是可以肯定，答案不会超过这四种可能性的范围
    // 那么我们分别来看一下，这几种可能性怎么调用后续的递归。
    // a) 最长公共子序列，一定不以str1[i]字符结尾、也一定不以str2[j]字符结尾
    //    如果是这种情况，那么有没有str1[i]和str2[j]就根本不重要了，因为这两个字符一定没用啊
    //    所以砍掉这两个字符，最长公共子序列 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度(后续递归)
    // b) 最长公共子序列，可能以str1[i]字符结尾、但是一定不以str2[j]字符结尾
    //    如果是这种情况，那么我们可以确定str2[j]一定没有用，要砍掉；但是str1[i]可能有用，所以要保留
    //    所以，最长公共子序列 = str1[0...i]与str2[0...j-1]的最长公共子序列长度(后续递归)
    // c) 最长公共子序列，一定不以str1[i]字符结尾、但是可能以str2[j]字符结尾
    //    跟上面分析过程类似，最长公共子序列 = str1[0...i-1]与str2[0...j]的最长公共子序列长度(后续递归)
    // d) 最长公共子序列，必须以str1[i]字符结尾、也必须以str2[j]字符结尾
    //    同时可以看到，可能性d)存在的条件，一定是在str1[i] == str2[j]的情况下，才成立的
    //    所以，最长公共子序列总长度 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度(后续递归) + 1(共同的结尾)
    // 综上，四种情况已经穷尽了所有可能性。四种情况中取最大即可
    public static int process(char[] s1, char[] s2, int i1, int i2) {
        if (i1 == 0 && i2 == 0) {
            // str1[0..0]和str2[0..0]，都只剩一个字符了
            // 那如果字符相等，公共子序列长度就是1，不相等就是0
            // 这显而易见
            return s1[i1] == s2[i2] ? 1 : 0;
        }
        if (i1 == 0) {
            if (s1[i1] == s2[i2]) {
                return 1;
            } else {
                return process(s1, s2, i1, i2 - 1);
            }
        }
        if (i2 == 0) {
            if (s1[i1] == s2[i2]) {
                return 1;
            } else {
                return process(s1, s2, i1 - 1, i2);
            }
        }

        int p1 = process(s1, s2, i1 - 1, i2);
        int p2 = process(s1, s2, i1, i2 - 1);
        int p3 = s1[i1] == s2[i2] ? 1 + process(s1, s2, i1 - 1, i2 - 1) : 1;
        return Math.max(p1, Math.max(p2, p3));
    }

    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] dp = new int[s1.length()][s2.length()];
        dp[0][0] = c1[0] == c2[0] ? 1 : 0;
        for (int i = 1; i < s2.length(); i++) {
            dp[0][i] = c1[0] == c2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < s1.length(); i++) {
            dp[i][0] = c1[i] == c2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                int res = Math.max(dp[i - 1][j], dp[i][j - 1]);
                res = Math.max(res, c1[i] == c2[j] ? 1 + dp[i - 1][j - 1] : 0);
                dp[i][j] = res;
            }
        }
        return dp[s1.length() - 1][s2.length() - 1];
    }
}
