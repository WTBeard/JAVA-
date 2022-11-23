package zs.slg.recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 */
public class PrintAllSubsquences2 {

    public static List<String> subsNoRepeat(String s) {
        Set<String> res = new HashSet<>();
        if (s == null || s.length() == 0) return new ArrayList<>();
        char[] chars = s.toCharArray();
        process(chars,"",res,0);

        return new ArrayList<>(res);
    }

    public static void process(char[] chars, String s, Set<String> res, int index) {
        if (chars.length == index) {
            res.add(s);
            return;
        }
        process(chars, s, res, index + 1);
        process(chars, s + chars[index], res, index + 1);
    }

    public static void main(String[] args) {
        String a = "acccc";
        List<String> subs = subsNoRepeat(a);
        subs.forEach(ss -> System.out.print(ss + " "));
    }
}
