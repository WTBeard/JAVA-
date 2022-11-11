package zs.slg.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题
 * 给定很多线段，每个线段都有两个数[start, end]，表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 *
 * 1     5
 *  2   4
 *   3  4
 *      4  6
 *  小跟堆:
 *  - 5
 *  - 2 < 5   4,5
 *  - 3 < 4   4,4,5
 *  - 4 = 4   5
 */
public class CoverMax {

    public static int maxCover(int[][] lines) {
        if (lines == null || lines.length == 0) return 0;

        Arrays.sort(lines, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!queue.isEmpty() && lines[i][0] >= queue.peek()) {
                queue.poll();
            }
            queue.add(lines[i][1]);
            max = Math.max(max,queue.size());
        }
        return max;
    }
}
