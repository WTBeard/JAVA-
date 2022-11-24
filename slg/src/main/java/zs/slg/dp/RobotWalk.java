package zs.slg.dp;

/**
 * 假设有排成一行的N个位置记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数
 */
public class RobotWalk {

    public static int ways1(int N, int start, int aim, int K) {
        if (start < 1 || start > N || K <= 0 || aim < 1 || aim > N) return 0;
        return process1(N, aim, start, K);
    }

    public static int process1(int N, int aim, int start, int K) {
        if (K == 0) {
            return start == aim ? 1 : 0;
        }
        if (start == 1) {
            return process1(N, aim, 2, K - 1);
        }
        if (start == N) {
            return process1(N, aim, N - 1, K - 1);
        }

        return process1(N, aim, start + 1, K - 1) + process1(N, aim, start - 1, K - 1);
    }

    public static int ways2(int N, int start, int aim, int K) {
        if (start < 1 || start > N || K <= 0 || aim < 1 || aim > N) return 0;
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;  // 剩下0步 走到 aim
        for (int i = 1; i <= K; i++) { // 一步一步走
            dp[1][i] = dp[2][i - 1]; // 机器人来到1位置，那么下一步只能往右来到2位置  步数减一
            for (int j = 2; j < N; j++) { // 哪个位置
                dp[j][i] = dp[j + 1][i - 1] + dp[j - 1][i - 1]; // 机器人来到中间位置，那么下一步可以往左走或者往右走 步数减一
            }
            dp[N][i] = dp[N - 1][i - 1]; // 机器人来到N位置，那么下一步只能往左来到N-1位置 步数减一
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
//        System.out.println(ways3(5, 2, 4, 6));
    }
}
