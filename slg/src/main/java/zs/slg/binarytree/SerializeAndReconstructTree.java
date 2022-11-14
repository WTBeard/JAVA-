package zs.slg.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化和反序列化
 */
public class SerializeAndReconstructTree {

    public static Queue<String> preSerial(Node head) {
        if (head == null) return null;

        Queue<String> queue = new LinkedList<>();
        pre(head, queue);
        return queue;
    }

    public static void pre(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.value));
            pre(head.left, queue);
            pre(head.right, queue);
        }
    }


    public static Queue<String> posSerial(Node head) {
        if (head == null) return null;
        Queue<String> queue = new LinkedList<>();
        pos(head, queue);
        return queue;
    }

    public static void pos(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            pos(head.left, queue);
            pos(head.right, queue);
            queue.add(String.valueOf(head.value));
        }
    }


    public static Queue<String> levelSerial(Node head) {
        if (head == null) return null;
        Queue<String> res = new LinkedList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        res.add(String.valueOf(head.value));
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                res.add(String.valueOf(head.left.value));
                queue.add(head.left);
            } else {
                res.add(null);
            }
            if (head.right != null) {
                res.add(String.valueOf(head.right.value));
                queue.add(head.right);
            } else {
                res.add(null);
            }
        }
        return res;
    }

    public static Node buildByPreQueue(Queue<String> prelist) {
        if (prelist == null || prelist.size() == 0) return null;
        return buildPre(prelist);
    }

    public static Node buildPre(Queue<String> prelist) {
        if (prelist == null || prelist.size() == 0) return null;
        String value = prelist.poll();
        if (value == null) {
            return null;
        }
        Node root = generateNode(prelist.poll());
        root.left = buildPre(prelist);
        root.right = buildPre(prelist);
        return root;
    }


    public static Node buildByPosQueue(Queue<String> poslist) {
        if (poslist == null || poslist.size() == 0) return null;
        String value = poslist.poll();
        if (value == null) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!poslist.isEmpty()){
            stack.push(poslist.poll());
        }
        return buildPos(stack);
    }

    public static Node buildPos(Stack<String> stack) {
        if (stack == null || stack.isEmpty()) return null;
        Node root = generateNode(stack.pop());
        root.right = buildPos(stack);
        root.left = buildPos(stack);
        return root;
    }


    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) return null;

        Node root = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()){
            Node node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }
        }
        return root;
    }

    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.parseInt(val));
    }
}
