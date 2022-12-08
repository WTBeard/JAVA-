package zs.slg.kmp;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两棵二叉树的头节点head1和head2，返回head1中是否有某个子树的结构和head2完全一样
 */
public class TreeEqual {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    public static boolean isSubtree(TreeNode big, TreeNode small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        List<Integer> bigList = preSerial(big);
        List<Integer> smallList = preSerial(small);

        int[] bigArr = new int[bigList.size()];
        for (int i = 0; i < bigList.size(); i++) {
            bigArr[i] = bigList.get(i);
        }

        int[] smallArr = new int[smallList.size()];
        for (int i = 0; i < smallList.size(); i++) {
            smallArr[i] = smallList.get(i);
        }
        return getIndexOf(bigArr,smallArr) != -1;
    }

    public static List<Integer> preSerial(TreeNode tree){
        List<Integer> ans = new ArrayList<>();
        pre(tree,ans);
        return ans;
    }

    public static void pre(TreeNode tree,List<Integer> ans){
        if (tree == null){
            ans.add(null);
        }else {
            ans.add(tree.val);
            pre(tree.left,ans);
            pre(tree.right,ans);
        }
    }

    public static int getIndexOf(int[] s1 ,int[] s2){
        if (s1.length > s2.length) return -1;
        int i1 = 0;
        int i2 = 0;
        int[] next = nextArr(s2);

        while (i1 < s1.length && i2 < s2.length){
            if (s1[i1] == s2[i2]){
                i1++;
                i2++;
            }else if (next[i2] == -1){
               i1++;
            }else {
                i2 = next[i2];
            }
        }
        return s2.length == i2 ? i1 - i2 : -1;
    }

    public static int[] nextArr(int[] s2){
        if (s2.length == 1) return new int[]{-1};

        int[] next = new int[s2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length){
            if (s2[i-1] == s2[cn]){
                next[i++] = ++cn;
            }else if (cn > 0){
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }
}
