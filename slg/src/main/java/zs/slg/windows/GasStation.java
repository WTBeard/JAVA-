package zs.slg.windows;

import java.util.LinkedList;

/**
 * 加油站的良好出发点问题
 * https://leetcode.com/problems/gas-station
 */
public class GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) return 0;

        int n = gas.length;
        int m = n << 1;
        int[] arr = new int[m];
        // gas - cos
        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + n] = gas[i] - cost[i];
        }
        // 累加和
        for (int i = 1; i < m; i++) {
            arr[i] += arr[i-1];
        }

        LinkedList<Integer> w = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]){
                w.pollLast();
            }
            w.addLast(i);
        }

        boolean[] res = new boolean[n];
        int index = 0;
        int offset = 0;
        for (int j = n; j < m; j++, offset = arr[index++]) {
            if (arr[w.peekFirst()] - offset >= 0) {
                res[index] = true;
            }
            if (w.peekFirst() == index) {
                w.pollFirst();
            }
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
                w.pollLast();
            }
            w.addLast(j);
        }

        for (int i = 0; i < n; i++) {
            if (res[i]) return i;
        }
        return -1;
    }
}
