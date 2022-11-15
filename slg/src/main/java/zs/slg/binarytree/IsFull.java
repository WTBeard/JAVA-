package zs.slg.binarytree;

public class IsFull {

    public static boolean isFull(Node head) {
        if (head == null) {
            return true;
        }
        return process2(head).isFull;
    }

    public static class Info {
        public boolean isFull;
        public int height;

        public Info(boolean f, int h) {
            isFull = f;
            height = h;
        }
    }

    public static Info process2(Node h) {
        if (h == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process2(h.left);
        Info rightInfo = process2(h.right);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(isFull, height);
    }
}
