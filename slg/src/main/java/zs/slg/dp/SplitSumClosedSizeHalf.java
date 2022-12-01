package zs.slg.dp;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 */
public class SplitSumClosedSizeHalf {

    public static int right(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }
        if (arr.length % 2 == 0) {
            return process(arr, 0, arr.length / 2, sum / 2);
        } else {
            int p1 = process(arr, 0, arr.length / 2, sum / 2);
            int p2 = process(arr, 0, arr.length / 2 + 1, sum / 2);
            return Math.max(p1, p2);
        }

    }

    public static int process(int[] arr, int index, int pick, int rest) {
        if (arr.length == index) {
            return pick == 0 ? 0 : -1;
        }
        if (pick == 0) return 0;
        int p1 = process(arr, index + 1, pick, rest);
        int p2 = 0;
        if (arr[index] <= rest) {
            p2 = process(arr, index + 1, pick - 1, rest - arr[index]);
            if (p2 != -1) {
                p2 += arr[index];
            }
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
        int[][][] dp = new int[n + 1][n / 2 + 2][sum / 2 + 1];
        for (int p = 1; p < n / 2 + 2; p++) {
            for (int r = 0; r < sum / 2 + 1; r++) {
                dp[n][p][r] = -1;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int p = 0; p < n / 2 + 2; p++) {
                for (int r = 0; r < sum / 2 + 1; r++) {
                    int p1 = dp[i + 1][p][r];
                    int p2 = 0;
                    if (arr[i] <= r) {
                        p2 = dp[i + 1][p - 1][r - arr[i]];
                        if (p2 != -1) {
                            p2 += arr[i];
                        }
                    }
                    dp[i][p][r] = Math.max(p1, p2);
                }
            }
        }
        if (arr.length % 2 == 0) {
            return dp[0][arr.length / 2][sum / 2];
        } else {
            int p1 = dp[0][arr.length / 2][sum / 2];
            int p2 = dp[0][arr.length / 2 + 1][sum / 2];
            return Math.max(p1, p2);
        }
    }

}
