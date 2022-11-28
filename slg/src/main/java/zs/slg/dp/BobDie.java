package zs.slg.dp;

/**
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class BobDie {

    public static double livePosibility1(int row, int col, int k, int N, int M) {
        if (row > N || row < 0 || col > M || col < 0 || k < 0) return 0;
        return process(row, col, k, N, M) / Math.pow(4, k);
    }

    public static int process(int row, int col, int rest, int N, int M) {
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        if (rest == 0) return 1;

        int ways = process(row + 1, col, rest - 1, N, M);
        ways += process(row - 1, col, rest - 1, N, M);
        ways += process(row, col + 1, rest - 1, N, M);
        ways += process(row, col - 1, rest - 1, N, M);
        return ways;
    }


    public static double livePosibility2(int row, int col, int k, int N, int M) {
        if (row > N || row < 0 || col > M || col < 0 || k < 0) return 0;

        int[][][] dp = new int[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;    // 还在棋盘中！
            }
        }
        for (int rest = 1; rest < k + 1; rest++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    dp[x][y][rest] = pick(dp, x + 1, y, rest - 1)
                            + pick(dp, x - 1, y, rest - 1)
                            + pick(dp, x, y + 1, rest - 1)
                            + pick(dp, x, y - 1, rest - 1);
                }
            }
        }
        return dp[row][col][k] / Math.pow(4,k);
    }

    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x == dp.length || y < 0 || y == dp[0].length) {
            return 0;
        }
        return dp[x][y][rest];
    }
}
