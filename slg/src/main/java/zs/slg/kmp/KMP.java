package zs.slg.kmp;

/**
 * KMP 判断是否是子串,返回子串的起始位置
 */
public class KMP {

    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2.length() < 1) return -1;
        if (s1.length() < s2.length()) return -1;

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[] next = nextArray(c2);

        int i1 = 0;
        int i2 = 0;
        while (i1 < s1.length() && i2 < s2.length()) {
            if (c1[i1] == c2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == s2.length() ? i1 - i2 : -1;
    }

    public static int[] nextArray(char[] c2) {
        if (c2.length == 1) return new int[]{-1};

        int[] next = new int[c2.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < c2.length) {
            if (c2[i - 1] == c2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
