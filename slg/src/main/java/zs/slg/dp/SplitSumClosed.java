package zs.slg.dp;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 */
public class SplitSumClosed {

    public static int right(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }
        return process(arr, 0, sum / 2);
    }

    public static int process(int[] arr, int index, int sum) {
        if (index == arr.length) {
            return 0;
        }
        int p1 = process(arr, index + 1, sum);
        int p2 = Integer.MIN_VALUE;
        if (sum - arr[index] >= 0) {
            p2 = arr[index] + process(arr, index + 1, sum - arr[index]);
        }
        return Math.max(p1, p2);
    }


    public static int dp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }

        int n = arr.length;
        int[][] dp = new int[n + 1][sum / 2 + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest < sum / 2 + 1; rest++) {
                int p1 = dp[i + 1][rest];
                int p2 = Integer.MIN_VALUE;
                if (rest - arr[i] >= 0) {
                    p2 = arr[i] + dp[i + 1][sum - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum / 2];
    }

}
