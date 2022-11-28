package zs.slg.dp;

/**
 * 背包问题
 * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值
 * 给定一个正数bag，表示一个载重bag的袋子，装的物品不能超过这个重量
 * 返回能装下的最大价值
 */
public class Knapsack {

    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || w.length == 0 || v == null || v.length == 0 || bag <= 0) return 0;
        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {   // 重量有可能等于 0
            return -1;
        }
        if (index == w.length) {
            return 0;
        }

        int p1 = process(w, v, index + 1, rest);  // 不要
        int p2 = process(w, v, index + 1, rest - w[index]); // 要
        if (p2 != -1) {
            p2 = v[index] + p2;
        }
        return Math.max(p1, p2);
    }


    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || w.length == 0 || v == null || v.length == 0 || bag <= 0) return 0;
        int[][] dp = new int[w.length + 1][bag + 1];
        for (int index = w.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];  // 不要
                int p2 = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]]; // 要
                if (p2 != -1) {
                    p2 = v[index] + p2;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
