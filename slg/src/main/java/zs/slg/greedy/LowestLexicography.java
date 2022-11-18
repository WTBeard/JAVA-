package zs.slg.greedy;

import java.util.Arrays;

/**
 * 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，返回所有可能的拼接结果中字典序最小的结果
 */
public class LowestLexicography {

    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) return null;

        Arrays.sort(strs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        String res = "";
        for (String s:strs) {
            res += s;
        }
        return res;
    }
}
