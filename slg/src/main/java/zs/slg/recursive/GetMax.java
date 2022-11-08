package zs.slg.recursive;

/**
 * 用递归行为得到数组中的最大值
 */
public class GetMax {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) return arr[l];

        int mid = l + ((r - l) >> 1);
        int left = process(arr, l, mid);
        int right = process(arr, mid + 1, r);
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        int[] arr = {3,5,2,7,2,6,1,8};
        int max = getMax(arr);
        System.out.println(max);
    }
}
