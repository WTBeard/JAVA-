package zs.slg.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大宽度
 *     1
 *   2   3
 * 4  5 6  7
 */
public class TreeMaxWidth {

    public static int maxWidthUseMap(Node head) {
        if (head == null) return 0;

        Node curEnd = head;
        Node nextEnd = null;
        int max = 0;
        int count = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            count++;
            if (node.left != null){
                nextEnd = node.left;
                queue.add(node.left);
            }
            if (node.right != null){
                nextEnd = node.right;
                queue.add(node.right);
            }
            if (node == curEnd){
                max = Math.max(max,count);
                count = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }
}
