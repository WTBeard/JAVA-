package zs.slg.fibonacci;

/**
 * 台阶方法数问题
 * 一个人可以一次往上迈1个台阶，也可以迈2个台阶，返回迈上N级台阶的方法数
 */
public class FibonacciProblem2 {

    public static int s1(int n) {
        if (n < 0) return 0;
        if (n == 1 || n == 2) return n;
        return s1(n - 1) + s1(n - 2);
    }

    public static int s2(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return n;
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] res = power(base, n - 2);
        return 2*res[0][0] + res[1][0];
    }

    public static int[][] power(int[][] m, int n) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = m;
        for (; n != 0; n >>= 1) {
            if ((n & 1) != 0){
                res = product(res,t);
            }
            t = product(t,t);
        }
        return res;
    }

    public static int[][] product(int[][] m, int[][] t) {
        int[][] res = new int[m.length][m[0].length];
        int n = m.length;
        int k = m[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < k; l++) {
                    res[i][j] = m[i][l] + m[l][j];
                }
            }
        }
        return res;
    }
}
