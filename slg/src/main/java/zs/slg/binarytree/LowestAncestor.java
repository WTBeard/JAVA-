package zs.slg.binarytree;

/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先
 */
public class LowestAncestor {

    public static class Info {
        boolean findA;
        boolean findB;
        public Node ans;

        public Info(boolean findA, boolean findB, Node ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    public static Node lowestAncestor(Node head, Node a, Node b) {
        return process(head, a, b).ans;
    }

    public static Info process(Node head, Node a, Node b) {
        if (head == null) return new Info(false, false, null);

        Info leftInfo = process(head.left, a, b);
        Info rightInfo = process(head.right, a, b);

        boolean findA = leftInfo.findA || rightInfo.findA || head == a;
        boolean findB = leftInfo.findB || rightInfo.findB || head == b;
        Node ans = null;
        if (leftInfo.ans != null) ans = leftInfo.ans;
        else if (rightInfo.ans != null) ans = rightInfo.ans;
        else if (head == a && (head.left == b || head.right == b)) ans = head;
        else if (findA && findB) ans = head;
        return new Info(findA, findB, ans);
    }
}
