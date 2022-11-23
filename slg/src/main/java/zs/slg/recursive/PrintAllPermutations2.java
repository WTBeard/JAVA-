package zs.slg.recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintAllPermutations2 {

    public static List<String> permutation3(String s) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        char[] chars = s.toCharArray();
        Set<String> res = new HashSet<>();
        process(res,chars,0);
        return new ArrayList<>(res);
    }

    public static void process(Set<String> res,char[] chars,int index){
        if (index == chars.length){
            res.add(String.valueOf(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars,index,i);
            process(res,chars,index+1);
            swap(chars,index,i);
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
        System.out.println("=======");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }
    }

}
