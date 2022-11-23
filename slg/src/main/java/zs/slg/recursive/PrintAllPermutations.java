package zs.slg.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 */
public class PrintAllPermutations {

    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process(str, ans, 0);
        return ans;
    }

    public static void process(char[] chars, List<String> res, int index) {
        if (index == chars.length) {
            res.add(String.valueOf(chars));
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            process(chars, res, index + 1);
            swap(chars, index, i);
        }
    }

    public static void swap(char[] chars, int i, int j) {
        if (i == j) return;
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }

    }
}
