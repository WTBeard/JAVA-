package zs.slg.binarytree;

/**
 * 判断二叉树是不是完全二叉树
 */
public class IsCBT {

    public static class Info {
        boolean isFBT;
        boolean isCBT;
        int high;

        public Info(boolean isFBT, boolean isCBT, int high) {
            this.isFBT = isFBT;
            this.isCBT = isCBT;
            this.high = high;
        }
    }

    public static boolean isCBT(Node head) {
        if (head == null) return true;

        return process(head).isCBT;
    }

    public static Info process(Node head) {
        if (head == null) return new Info(true, true, 0);

        Info left = process(head.left);
        Info right = process(head.right);

        boolean isFBT = left.isFBT && right.isFBT && left.high == right.high;

        boolean isCBT = false;
        if (isFBT) isCBT = true;
        else {
            if (left.isCBT && right.isCBT){
                if (left.isCBT && right.isFBT && left.high == right.high + 1) {
                    isCBT = true;
                }
                if (left.isFBT && right.isFBT && left.high == right.high + 1) {
                    isCBT = true;
                }
                if (left.isFBT && right.isCBT && left.high == right.high) {
                    isCBT = true;
                }
            }
        }
        return new Info(isFBT, isCBT, Math.max(left.high, right.high) + 1);
    }
}
