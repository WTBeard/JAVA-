package zs.slg.monotonousStack;

import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 */
public class AllTimesMinToMax {

    public static int max(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }

        int res = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer cur = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                int subSum = left == -1 ? sum[right - 1] : sum[right - 1] - sum[left];
                res = Math.max(res,subSum * arr[cur]);
            }
            stack.add(i);
        }
        while (!stack.isEmpty()){
            Integer cur = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int subSum = left == -1 ? sum[arr.length - 1]: sum[arr.length - 1] - sum[left];
            res = Math.max(res,subSum * arr[cur]);
        }
        return res;
    }

    public static int max1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }

        int res = Integer.MIN_VALUE;
        int[] stack = new int[arr.length];
        int size = -1;

        for (int i = 0; i < arr.length; i++) {
            while (size != -1 && arr[size] >= arr[i]) {
                int cur = stack[size--];
                int left = size == -1 ? -1 : stack[size];
                int right = i;
                int subSum = left == -1 ? sum[right - 1] : sum[right - 1] - sum[left];
                res = Math.max(res,subSum * arr[cur]);
            }
            stack[++size] = i;
        }
        while (size != -1){
            int cur = stack[size--];
            int left = size == -1 ? -1 : stack[size];
            int subSum = left == -1 ? sum[arr.length - 1]: sum[arr.length - 1] - sum[left];
            res = Math.max(res,subSum * arr[cur]);
        }
        return res;
    }
}
