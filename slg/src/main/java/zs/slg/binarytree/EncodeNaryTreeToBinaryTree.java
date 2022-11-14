package zs.slg.binarytree;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.ArrayList;
import java.util.List;

/**
 * N叉树如何通过二叉树来序列化、并完成反序列化
 * Leetcode题目：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
 */
public class EncodeNaryTreeToBinaryTree {

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode encode(Node root) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val);
        node.left = en(root.children);
        return node;
    }

    public TreeNode en(List<Node> root) {
        if (root == null || root.size() == 0) return null;

        TreeNode head = null;
        TreeNode cur = null;
        for (Node n:root) {
            TreeNode node = new TreeNode(n.val);
            if (head == null){
                head = node;
                cur = node;
            }else {
                cur.right = node;
                cur = node;
            }
            cur.left = en(n.children);
        }
        return head;
    }


    public Node decode(TreeNode root) {
        if (root == null) return null;

        return new Node(root.val,de(root.left));
    }

    public List<Node> de(TreeNode root) {
        if (root == null) return null;
        List<Node> res = new ArrayList<>();
        while (root != null){
            Node node = new Node(root.val, de(root.left));
            res.add(node);
            root = root.right;
        }
        return res;
    }

}
