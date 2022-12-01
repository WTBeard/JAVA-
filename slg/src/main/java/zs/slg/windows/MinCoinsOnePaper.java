package zs.slg.windows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 */
public class MinCoinsOnePaper {

    public static int minCoins(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        int p1 = process(arr, index + 1, rest);
        int p2 = process(arr, index + 1, rest - arr[index]);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dps = new int[n + 1][aim + 1];
        for (int rest = 1; rest < aim + 1; rest++) {
            dps[n][rest] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = dps[index + 1][rest];
                int p2 = rest - arr[index] >= 0 ? dps[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
                if (p2 != Integer.MAX_VALUE) {
                    p2++;
                }
                dps[index][rest] = Math.min(p1, p2);
            }
        }
        return dps[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dps = new int[n + 1][aim + 1];
        for (int rest = 1; rest < aim + 1; rest++) {
            dps[n][rest] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = dps[index + 1][rest];
                int p2 = rest - arr[index] >= 0 ? dps[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
                if (p2 != Integer.MAX_VALUE) {
                    p2++;
                }
                dps[index][rest] = Math.min(p1, p2);
            }
        }
        return dps[0][aim];
    }

    public static class Info {
        int[] coin;
        int[] zhangs;

        public Info(int[] coin, int[] zhangs) {
            this.coin = coin;
            this.zhangs = zhangs;
        }
    }

    public static int minCoins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return process(info.coin, info.zhangs, 0, aim);
    }

    public static int process(int[] coins, int[] zhangs, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }

        int p1 = Integer.MAX_VALUE;
        for (int z = 0; z <= zhangs[index] && z * coins[index] <= rest; z++) {
            int process = process(coins, zhangs, index + 1, rest - z * coins[index]);
            if (process != Integer.MAX_VALUE) {
                p1 = Math.min(p1, process + z);
            }
        }
        return p1;
    }


    public static Info getInfo(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            if (map.containsKey(j)) {
                map.put(j, map.get(j) + 1);
            } else {
                map.put(j, 1);
            }
        }
        int n = map.size();
        int[] coins = new int[n];
        int[] zhangs = new int[n];
        int i = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            coins[i] = e.getKey();
            zhangs[i] = e.getValue();
            i++;
        }
        return new Info(coins, zhangs);
    }

    public static int dp3(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coin = info.coin;
        int[] zhang = info.zhangs;
        int n = coin.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int rest = 1; rest < aim + 1; rest++) {
            dp[n][rest] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                for (int z = 1; z <= zhang[i] && z * coin[i] <= rest; z++) {
                    int process = dp[i + 1][rest - z * coin[i]];
                    if (process != Integer.MAX_VALUE) {
                        dp[i][rest] = Math.min(dp[i][rest], process + z);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    public static int dp4(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coin = info.coin;
        int[] zhang = info.zhangs;
        int n = coin.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int rest = 1; rest < aim + 1; rest++) {
            dp[n][rest] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                dp[i][rest] = dp[i + 1][rest];



                for (int z = 1; z <= zhang[i] && z * coin[i] <= rest; z++) {
                    int process = dp[i + 1][rest - z * coin[i]];
                    if (process != Integer.MAX_VALUE) {
                        dp[i][rest] = Math.min(dp[i][rest], process + z);
                    }
                }
            }
        }
        return dp[0][aim];
    }


}
