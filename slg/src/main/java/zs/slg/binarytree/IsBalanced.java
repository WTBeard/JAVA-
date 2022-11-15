package zs.slg.binarytree;

/**
 * 判断二叉树是不是平衡二叉树
 */
public class IsBalanced {

    public static class Info{
        boolean isBalanced;
        int high;

        public Info(boolean isBalanced, int high) {
            this.isBalanced = isBalanced;
            this.high = high;
        }
    }

    public static boolean isBalanced(Node head){
        if (head == null) return true;
        return process(head).isBalanced;
    }

    public static Info process(Node head){
        if (head == null) return new Info(true,0);

        Info left = process(head.left);
        Info right = process(head.right);

        int high = Math.max(left.high,right.high) + 1;

        boolean isBalanced = false;
        if (left.isBalanced && right.isBalanced){
            isBalanced = Math.abs(left.high - right.high) < 2;
        }
        return new Info(isBalanced,high);
    }
}
