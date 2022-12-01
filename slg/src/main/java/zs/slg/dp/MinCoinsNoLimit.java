package zs.slg.dp;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class MinCoinsNoLimit {

    public static int minCoins(int[] arr, int aim) {
        if (arr == null || arr.length == 0) return 0;
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ways = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            int process = process(arr, index + 1, rest - zhang * arr[index]);
            if (process != Integer.MAX_VALUE) {
                ways = Math.min(ways, zhang + process);
            }
        }
        return ways;
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) return 0;

        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[arr.length][j] = Integer.MAX_VALUE;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 1; rest < aim + 1; rest++) {
                int ways = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    int process = dp[index + 1][rest - zhang * arr[index]];
                    if (process != Integer.MAX_VALUE) {
                        ways = Math.min(ways, zhang + process);
                    }
                    dp[index][rest] = ways;
                }
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) return 0;

        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[arr.length][j] = Integer.MAX_VALUE;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 1; rest < aim + 1; rest++) {
                dp[index][rest] =  dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE){
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }
}
