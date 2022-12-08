package zs.slg.kmp;

/**
 * 判断str1和str2是否互为旋转字符串
 */
public class IsRotation {

    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        a += a;
        return getIndexOd(a.toCharArray(), b.toCharArray()) == -1;
    }

    public static int getIndexOd(char[] a, char[] b) {
        int[] next = nextArr(b);
        int i1 = 0;
        int i2 = 0;

        while (i1 < a.length && i2 < b.length) {
            if (a[i1] == b[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == b.length ? i1 - i2 : -1;
    }

    public static int[] nextArr(char[] b) {
        int[] res = new int[b.length];
        res[0] = -1;
        res[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < res.length) {
            if (b[i - 1] == b[cn]) {
                res[i++] = ++cn;
            } else if (cn > 0) {
                cn = res[cn];
            } else {
                res[i++] = 0;
            }
        }
        return res;
    }
}
