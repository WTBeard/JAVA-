package zs.slg.monotonousStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 单调栈
 * 每个位置,左边离他最近的比他小的位置,右边离他最近的比他小的位置
 * （无重复数+有重复数）
 */
public class MonotonousStack {

    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer cur = stack.pop();
                res[cur][0] = stack.isEmpty() ? -1 : stack.peek();
                res[cur][1] = i;
            }
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            res[cur][0] = stack.isEmpty() ? -1 : stack.peek();
            res[cur][1] = -1;
        }
        return res;
    }

    public static int[][] getNearLess(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> curs = stack.pop();
                for (Integer cur : curs) {
                    res[cur][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    res[cur][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(i);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.add(list);
            }
        }
        while (!stack.isEmpty()){
            List<Integer> curs = stack.pop();
            for (Integer cur : curs) {
                res[cur][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                res[cur][1] = -1;
            }
        }
        return res;
    }
}
