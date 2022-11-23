package zs.slg.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列
 */
public class PrintAllSubsquences {

    public static List<String> subs(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        char[] chars = s.toCharArray();
        process(chars, 0, "", res);
        return res;
    }

    public static void process(char[] chars, int index, String s, List<String> res) {
        if (index == chars.length) {
            res.add(s);
            return;
        }
        process(chars, index + 1, s, res); // 不要
        process(chars, index + 1, s + chars[index], res); // 要
    }


    public static void main(String[] args) {
        String s = "acccc";
        List<String> subs = subs(s);
        subs.forEach(ss -> System.out.print(ss + " "));
    }
}
