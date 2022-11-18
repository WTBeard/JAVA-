package zs.slg.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入正数数组costs、正数数组profits、正数K和正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目，不能并行的做项目。
 * 输出：最后获得的最大钱数
 */
public class IPO {

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        // 花费最小
        PriorityQueue<Program> minCst = new PriorityQueue<>(Comparator.comparingInt(o -> o.c));
        // 利益最大
        PriorityQueue<Program> maxPro = new PriorityQueue<>((o1, o2) -> o2.p - o1.p);
        for (int i = 0; i < Profits.length; i++) {
            minCst.add(new Program(Profits[i], Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minCst.isEmpty() && minCst.peek().c <= W) {
                maxPro.add(maxPro.poll());
            }
            // 都买不起,返回初始资金
            if (maxPro.isEmpty()) {
                return W;
            }
            // 取最大收益
            W += maxPro.poll().p;
        }
        return W;
    }
}
