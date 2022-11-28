package zs.slg.dp;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 */
public class Coffee {

    public static class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }

    public static int minTime1(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> machines = new PriorityQueue<>(new Comparator<Machine>() {
            @Override
            public int compare(Machine o1, Machine o2) {
                return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
            }
        });
        for (int j : arr) {
            machines.add(new Machine(0, j));
        }

        int[] drinks = new int[n];   // 喝完时间
        for (int i = 0; i < n; i++) {
            Machine machine = machines.poll();
            machine.timePoint = machine.timePoint + machine.workTime;
            machines.add(machine);
            drinks[i] = machine.timePoint;
        }
        return bestTime(drinks, a, b, 0, 0);
    }

    // drinks 所有杯子可以开始洗的时间
    // wash 单杯洗干净的时间（串行）
    // air 挥发干净的时间(并行)
    // free 洗的机器什么时候可用
    // drinks[index.....]都变干净，最早的结束时间（返回）
    public static int bestTime(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) return 0;

        int selfWash = Math.max(drinks[index], free) + wash;
        selfWash = Math.max(selfWash, bestTime(drinks, wash, air, index + 1, selfWash));

        int airTime = drinks[index] + air;
        airTime = Math.max(airTime, bestTime(drinks, wash, air, index + 1, free));
        return Math.min(selfWash, airTime);
    }

    public static int minTime2(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> machines = new PriorityQueue<>(new Comparator<Machine>() {
            @Override
            public int compare(Machine o1, Machine o2) {
                return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
            }
        });
        for (int j : arr) {
            machines.add(new Machine(0, j));
        }

        int[] drinks = new int[n];   // 喝完时间
        for (int i = 0; i < n; i++) {
            Machine machine = machines.poll();
            machine.timePoint = machine.timePoint + machine.workTime;
            machines.add(machine);
            drinks[i] = machine.timePoint;
        }

        int maxFree = 0;
        for (int i = 0; i < drinks.length; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + a;
        }
        int[][] dp = new int[drinks.length][maxFree + 1];
        for (int index = drinks.length - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                int selfClean1 = Math.max(drinks[index], free) + a;
                if (selfClean1 > maxFree) {
                    break; // 因为后面的也都不用填了
                }
                // index号杯子 决定洗
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);
                // index号杯子 决定挥发
                int selfClean2 = drinks[index] + b;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }
}
