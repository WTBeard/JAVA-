package zs.slg.windows;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 *
 * 1. L ~ R 的范围内  最大值 - 最小值 <= num, 那么 L ~ R范围内的所有子数组都是达标的
 * 2. L ~ R 的范围内  最大值 - 最小值不达标, 那么 L 变小,或者 R 变大 都是不会达标的
 * 所以方案
 * L 在 0 上,R 一直变大,直到不符合条件停止,计算结果 比如 0~99 就是 100
 * 然后 L 变大就是 1 , R再从刚刚停止的地方继续变大,直到不满足条件, 计算多少个
 * 依次...
 */
public class AllLessNumSubArray {

    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) return 0;

        LinkedList<Integer> maxQueue = new LinkedList<>();
        LinkedList<Integer> minQueue = new LinkedList<>();
        int res = 0;
        int R = 0;
        for (int L = 0; L < arr.length; L++) {
            while (R < arr.length){
                while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[R]){
                    maxQueue.pollLast();
                }
                maxQueue.addLast(R);

                while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[R]){
                    minQueue.pollLast();
                }
                minQueue.addLast(R);

                if (maxQueue.peekFirst() - minQueue.peekFirst() <= sum){
                    R++;
                }else {
                    break;
                }
            }
            res += R - L;
            if (maxQueue.peekFirst() == L){
                maxQueue.pollFirst();
            }
            if (minQueue.peekFirst() == L) {
                minQueue.pollFirst();
            }
        }
        return res;
    }
}
