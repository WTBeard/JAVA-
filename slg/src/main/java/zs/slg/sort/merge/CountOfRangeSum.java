package zs.slg.sort.merge;

/**
 * 给定一个数组arr，两个整数lower和upper，返回arr中有多少个子数组的累加和在[lower,upper]范围上
 * https://leetcode.com/problems/count-of-range-sum/
 * sum[i ... j] = arr[0 ... j] - arr[0 ... i-1]
 */
public class CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null) return 0;

        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = nums[i] + sum[i - 1];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] arr, int l, int r, int lower, int upper) {
        if (l == r) {
            return arr[l] >= lower && arr[l] <= upper ? 1 : 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid, lower, upper) + process(arr, mid + 1, r, lower, upper) + merge(arr, l, mid, r, lower, upper);
    }

    public static int merge(long[] arr, int l, int mid, int r, int lower, int upper) {
        int res = 0;
        int maxIndex = l;
        int minIndex = l;
        for (int i = mid + 1; i <= r; i++) {
            long max = arr[i] - lower;
            long min = arr[i] - upper;
            while (maxIndex <= mid && arr[maxIndex] <= max){
                maxIndex++;
            }
            while (minIndex <= mid && arr[minIndex] < min){
                minIndex++;
            }
            res += maxIndex - minIndex;
        }

        long[] help = new long[r - l + 1];
        int i = 0;
        int ls = l;
        int rs = mid + 1;
        while (ls <= mid && rs <= r){
            help[i++] = arr[ls] <= arr[rs] ? arr[ls++] : arr[rs++];
        }
        while (ls <= mid){
            help[i++] = arr[ls++];
        }
        while (rs <= r){
            help[i++] = arr[rs++];
        }
        for (i = 0; i < help.length; i++) {
            arr[i + l] = help[i];
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {-2147483647,0,-2147483647,2147483647};
        int i = countRangeSum(arr, -564, 3864);
        System.out.println(i);
    }
}
