package zs.slg.dp;

/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 * <p>
 * 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class PalindromeSubsequence {

    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        return longestCommonSubsequence1(s.toCharArray(), reverse(s));
    }

    public static char[] reverse(String s) {
        char[] reverse = new char[s.length()];
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            reverse[j++] = s.charAt(i);
        }
        return reverse;
    }

    public static int longestCommonSubsequence1(char[] s1, char[] s2) {
        int n = s1.length;
        int m = s2.length;
        int[][] dp = new int[n][m];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int i = 0; i < m; i++) {
            dp[0][i] = s1[0] == s2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int res = Math.max(dp[i][j - 1], dp[j][i - 1]);
                res = Math.max(res, s1[i] == s2[i] ? 1 + dp[i - 1][j - 1] : 0);
                dp[i][j] = res;
            }
        }
        return dp[n - 1][m - 1];
    }


    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        return process(s.toCharArray(), 0, s.length() - 1);
    }


    public static int process(char[] s, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l == r - 1) {
            return s[l] == s[r] ? 2 : 1;
        }
        int p1 = process(s, l + 1, r);
        int p2 = process(s, l, r - 1);
        int p3 = process(s, l + 1, r - 1);
        int p4 = s[l] == s[r] ? 2 + process(s, l + 1, r - 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int lpsl2(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        char[] chars = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        dp[s.length() - 1][s.length() - 1] = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = chars[i] == chars[i + 1] ? 2 : 1;
        }
        for (int i = s.length() - 3; i >= 0; i--) {
            for (int j = i + 2; j < s.length(); j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                if (chars[i] == chars[j]) {
                    dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }


}
