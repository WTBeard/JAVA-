package zs.slg.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *  最小生成树
 *  实现Prim算法
 *  所有点都连通的情况下 权重和最小
 */
public class Prim {

    public static Set<Edge> primMST(Graph graph) {
        Set<Edge> res = new HashSet<>();
        Set<Node> set = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        for (Node node : graph.nodes.values()) { // 防止森林
            if (set.contains(node)) continue;
            queue.addAll(node.edges);
            set.add(node);

            while (!queue.isEmpty()){
                Edge edge = queue.poll();
                Node to = edge.to;
                if (set.contains(to)) continue;
                set.add(to);
                res.add(edge);
                queue.addAll(to.edges);
            }
        }
        return res;
    }
}
