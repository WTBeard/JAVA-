package zs.slg.graph;

/**
 * 图 -> 边
 */
public class Edge {
    Node from;
    Node to;
    int weight;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
