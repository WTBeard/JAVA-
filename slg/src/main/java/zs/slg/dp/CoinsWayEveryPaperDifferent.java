package zs.slg.dp;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class CoinsWayEveryPaperDifferent {

    public static int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0) return 0;
        return process(arr, aim, 0);
    }

    public static int process(int[] arr, int rest, int index) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        if (rest == 0) {
            return 1;
        }
        int p1 = process(arr, rest - arr[index], index + 1);
        int p2 = process(arr, rest, index + 1);
        return p1 + p2;
    }


    public static void main(String[] args) {
        int i = coinWays(new int[]{1, 1, 1}, 2);
        System.out.println(i);
    }

    public static int dp(int[] arr, int aim) {
        if (arr == null || arr.length == 0) return 0;
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0);
            }
        }
        return dp[0][aim];
    }
}
