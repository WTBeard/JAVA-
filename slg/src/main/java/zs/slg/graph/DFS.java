package zs.slg.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 图的深度优先遍历
 */
public class DFS {

    /*
            1
           2  3
          4    5
     */
    public static void dfs(Node node) {
        if (node == null) return;

        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        set.add(node);
        stack.push(node);
        System.out.println(node.value); // 1

        while (!stack.isEmpty()) {
            Node pop = stack.pop();                // 1  / 2        / 4    / 1     / 3      / 5   / 3  / 1
            for (Node e : pop.next) {
                if (!set.contains(e)) {
                    set.add(e);
                    stack.push(pop);               // 1  /  2 1            / 1    / 3 1
                    stack.push(e);                 // 2 1 / 4 2 1          / 3 1  / 5 3 1   // 2 4 3 5
                    System.out.println(e.value);
                    break;
                }
            }
        }
    }

    public static void dfs2(Node node) {
        if (node == null) return;
        process(node);
    }

    public static void process(Node node) {
        if (node == null) return;

        System.out.println(node.value);
        for (Node e : node.next) {
            process(e);
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        node.next.add(node1);
        node.next.add(node2);
        Node node3 = new Node(4);
        node1.next.add(node3);
        Node node4 = new Node(5);
        node2.next.add(node4);
        dfs(node);
        System.out.println("============");
        dfs2(node);
    }
}
