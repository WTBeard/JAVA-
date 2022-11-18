package zs.slg.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲，给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多，返回最多的宣讲场次
 */
public class BestArrange {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange(Program[] programs) {
        if (programs == null || programs.length == 0) return 0;
        Arrays.sort(programs, Comparator.comparingInt(o -> o.end));

        int timeLine = 0;
        int sum = 0;
        for (Program p:programs) {
            if (timeLine <= p.start){
                sum++;
                timeLine = p.end;
            }
        }
        return sum;
    }
}
