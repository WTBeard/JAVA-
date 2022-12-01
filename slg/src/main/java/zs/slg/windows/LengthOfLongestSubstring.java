package zs.slg.windows;

import java.util.ArrayList;
import java.util.List;

public class LengthOfLongestSubstring {

    /**
     * 暴力递归解法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        return process(s.toCharArray(),0,new ArrayList<>());
    }

    public int process(char[] chars, int index, List<Character> paths){
        if (index == chars.length){
            int size = paths.size();
            paths.clear();
            return size;
        }
        if (paths.contains(chars[index])) {
            int size = paths.size();
            paths.clear();
            return size;
        }

        int p1 = 0;
        if (paths.size() == 0){
            p1 = process(chars, index + 1, paths); // 不要
        }
        paths.add(chars[index]);
        int p2 = process(chars, index + 1, paths); // 要
        return Math.max(p2,p1);
    }

    public static void main(String[] args) {
        int i = new LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb");
        System.out.println(i);
    }
}
