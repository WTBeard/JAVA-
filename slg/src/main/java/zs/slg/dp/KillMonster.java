package zs.slg.dp;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class KillMonster {

    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) return 0;
        return process(N, M, K) / Math.pow((M + 1), K);
    }

    public static long process(int hp, int M, int rest) {
        if (rest == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            return (long) Math.pow((M + 1), rest);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(hp - i, M, rest - 1);
        }
        return ways;
    }

    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int rest = 1; rest <= K ; rest++) {
            dp[rest][0] = (long) Math.pow((M + 1), rest);
            for (int hp = 1; hp <= N ; hp++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0){
                        ways += dp[rest - 1][hp - i];
                    }else {
                        ways += (long) Math.pow((M + 1), rest - 1);
                    }
                }
                dp[rest][hp] = ways;
            }
        }
        return dp[K][N] / Math.pow(M + 1, K);
    }

    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int rest = 1; rest <= K ; rest++) {
            dp[rest][0] = (long) Math.pow((M + 1), rest);
            for (int hp = 1; hp <= N ; hp++) {
                dp[rest][hp] = dp[rest - 1][hp] + dp[rest - 1][hp];
                if (hp - M - 1 >= 0){
                    dp[rest][hp] -= dp[rest-1][hp - M - 1];
                }else {
                    dp[rest][hp] -= (long) Math.pow((M + 1), rest - 1);
                }
            }
        }
        return dp[K][N] / Math.pow(M + 1, K);
    }
}
