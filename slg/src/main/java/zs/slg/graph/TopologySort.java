package zs.slg.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 实现图的拓扑排序
 * 1. 在图中找到所有入度为 0 的点输出
 * 2. 把所有入度为 0 的点在图中删掉,继续找入度为 0 的点输出,周而复始
 * 3. 图的所有点都被删除后,依次输出的顺序就是拓扑排序
 * 有向 无环
 * <p>
 * 利用 in (入度),把入度为 0 的拿出来,把他们的 out 边消掉,直到全部消掉
 */
public class TopologySort {

    public static List<Node> sortedTopology(Graph graph) {
        List<Node> res = new ArrayList<>();
        if (graph == null || graph.nodes == null) return res;

        Node inNode = null;
        for (Node node : graph.nodes.values()) {
            if (node.in == 0) {
                inNode = node;
                break;
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(inNode);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            res.add(node);
            for (Node e : node.next) {
                if (--e.in == 0){
                    queue.add(e);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        node.next.add(node1);
        node.next.add(node2);
        node1.in++;
        node2.in++;
        Node node3 = new Node(4);
        node1.next.add(node3);
        node3.in++;
        Node node4 = new Node(5);
        node2.next.add(node4);
        node4.in++;

        Graph graph = new Graph();
        graph.nodes.put(1,node);
        graph.nodes.put(2,node1);
        graph.nodes.put(3,node2);
        graph.nodes.put(4,node3);
        graph.nodes.put(5,node4);
        List<Node> nodes = sortedTopology(graph);
        for (Node n:nodes) {
            System.out.println(n.value);
        }
    }
}
