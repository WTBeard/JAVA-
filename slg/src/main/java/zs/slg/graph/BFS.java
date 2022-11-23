package zs.slg.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的宽度优先遍历
 */
public class BFS {

    public static void bfs(Node start) {
        if (start == null) return;

        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.value);
            for (Node e : node.next) {
                if (!set.contains(e)){
                    set.add(e);
                    queue.add(e);
                }
            }
        }
    }
}
