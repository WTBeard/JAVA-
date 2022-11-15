package zs.slg.binarytree;

/**
 * 判断二叉树是不是搜索二叉树
 */
public class IsBST {

    public static class Info{
        int max;
        int min;
        boolean isBST;

        public Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }

    public static boolean isBST(Node head) {
        if(head == null) return true;
        return process(head).isBST;
    }


    public static Info process(Node head) {
        if (head == null) return null;

        Info left = process(head.left);
        Info right = process(head.right);

        int max = head.value;
        if (left != null) {
            max = Math.max(max, left.max);
        }
        if (right != null) {
            max = Math.max(max, right.max);
        }
        int min = head.value;
        if (left != null) {
            min = Math.min(min, left.min);
        }
        if (right != null) {
            min = Math.min(min, right.min);
        }

        boolean isBST = true;
        if (left != null && !left.isBST) {
            isBST = false;
        }
        if (right != null && !right.isBST) {
            isBST = false;
        }
        if (left != null && left.max >= head.value) {
            isBST = false;
        }
        if (right != null && right.min <= head.value) {
            isBST = false;
        }
        return new Info(max,min,isBST);
    }


}
