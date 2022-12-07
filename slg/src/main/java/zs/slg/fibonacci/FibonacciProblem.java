package zs.slg.fibonacci;

/**
 * 斐波那契数列矩阵乘法方式的实现
 */
public class FibonacciProblem {

    public static int f1(int n) {
        if (n == 1 || n == 2) return 1;
        return f1(n - 1) + f1(n - 2);
    }

    /**
     * | a b |
     * | c d |
     * a + c = 2     b + d = 1
     * 2a + c = 3    2b + d = 2
     * a = 1  c = 1
     * b = 1  d = 0
     * <p>
     * | 1 1 |
     * | 1 0 |
     * <p>
     * (n,n-1) = (i1,i2) * 矩阵 的 n - 2 次方
     */
    public static int f3(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;

        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0){
                res = product(res,t);
            }
            t = product(t,t);
        }
        return res;
    }

    /**
     * | a b |  | w x |
     * | c d |  | y z |
     *
     * | a*w+b*y    a*x+b*z|
     * | c*w+d*y    c*x+d*z|
     */
    public static int[][] product(int[][] a, int[][] b) {
        int[][] res = new int[a.length][a[0].length];
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < k; l++) {
                    res[i][j] += a[i][l] + b[l][j];
                }
            }
        }
        return res;
    }
}
