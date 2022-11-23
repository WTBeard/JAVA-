package zs.slg.graph;

/**
 * 二维数组换换成 图结构
 */
public class GraphGenerator {

    /**
     * matrix 所有的边
     * N*3 的矩阵
     * [weight, from节点上面的值，to节点上面的值]
     * [ 5 , 0 , 7]
     * [ 3 , 0,  1]
     */
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        if(matrix == null || matrix.length == 0) return graph;

        for (int[] ints : matrix) {
            int weight = ints[0];
            int from = ints[1];
            int to = ints[2];

            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(from, new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(fromNode, toNode, weight);

            graph.edges.add(edge);

            fromNode.out++;
            fromNode.next.add(toNode);
            fromNode.edges.add(edge);
            toNode.in++;
        }
        return graph;
    }
}
