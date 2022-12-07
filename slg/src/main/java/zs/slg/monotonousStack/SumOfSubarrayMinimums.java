package zs.slg.monotonousStack;

import java.util.Stack;

/**
 * 给定一个数组arr，返回所有子数组最小值的累加和
 * 测试链接：https://leetcode.com/problems/sum-of-subarray-minimums/
 */
public class SumOfSubarrayMinimums {

    public static int subArrayMinSum3(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int[] stack = new int[arr.length];
        int[] left = leftLessEqual(arr,stack);
        int[] right = right(arr,stack);
        long res = 0L;
        for (int i = 0; i < arr.length ; i++) {
            int start = i - left[i];
            int end = right[i] - i;
            res += start * end * (long)arr[i];
            res %= 1000000007;
        }
        return (int) res;
    }

    public static int[] leftLessEqual(int[] arr,int[] stack){
        int[] left = new int[arr.length];
        int index = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            while (index != -1 && arr[stack[index]] >= arr[i]){
                int j = stack[index--];
                left[j] = i;
            }
            stack[++index] = i;
        }
        while (index != -1){
            int j = stack[index--];
            left[j] = -1;
        }
        return left;
    }

    public static int[] right(int[] arr,int[] stack){
        int index = -1;
        int[] right = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (index != -1 && arr[stack[index]] > arr[i]){
                int j = stack[index--];
                right[j] = i;
            }
            stack[++index] = i;
        }
        while (index != -1){
            int j = stack[index--];
            right[j] = arr.length;
        }
        return right;
    }

}
