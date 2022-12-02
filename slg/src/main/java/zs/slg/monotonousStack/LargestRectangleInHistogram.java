package zs.slg.monotonousStack;

import java.util.Stack;

/**
 * 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积
 */
public class LargestRectangleInHistogram {

    public static int largestRectangleArea1(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }
        int res = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]){
                Integer cur = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                int count = right - left - 1;
                res = Math.max(res,count * height[cur]);
            }
            stack.add(i);
        }
        while (!stack.isEmpty()){
            Integer cur = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int count = height.length - left - 1;
            res = Math.max(res,count * height[cur]);
        }
        return res;
    }


    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int[] stack = new int[height.length];
        int index = -1;

        for (int i = 0; i < height.length; i++) {
            while (index != -1 && height[stack[index]] >= height[i]){
                int cur = stack[index--];
                int left = index == -1 ? -1 : stack[index];
                int right = i;
                int count = right - left - 1;
                res = Math.max(res,count * height[cur]);
            }
            stack[++index] = i;
        }
        while (index != -1){
            int cur =  stack[index--];
            int left = index == -1 ? -1 : stack[index];
            int count = height.length - left - 1;
            res = Math.max(res,count * height[cur]);
        }
        return res;
    }
}
