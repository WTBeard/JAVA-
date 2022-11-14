package zs.slg.binarytree;

import java.util.Stack;

/**
 * 二叉树先序、中序、后序的非递归遍历
 * 好好体会
 */
public class UnRecursiveTraversalBT {

    public static void pre(Node head) {
        if (head == null) return;
        Stack<Node> queue = new Stack<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node node = queue.pop();
            System.out.println(node.value);
            if(node.right != null){
                queue.add(node.right);
            }
            if (node.left != null){
                queue.add(node.left);
            }
        }
    }

    public static void in(Node head) {
        if (head == null) return;
        Stack<Node> nodes = new Stack<>();
        while (!nodes.isEmpty() || head != null){
            if (head != null){
                nodes.push(head);
                head = head.left;
            }else {
                Node pop = nodes.pop();
                System.out.println(pop.value);
                head = pop.right;
            }
        }
    }


    public static void pos(Node head) {
        if (head == null) return;
        Stack<Node> s1 = new Stack<Node>();
        Stack<Node> s2 = new Stack<Node>();
        s1.push(head);
        while (!s1.isEmpty()){
            Node node = s1.pop();
            s2.push(node);
            if (node.left != null){
                s1.push(node.left);
            }
            if (node.right != null){
                s1.push(node.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " ");
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos(head);
        System.out.println("========");
    }

}
