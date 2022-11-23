package zs.slg.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图 -> 点
 */
public class Node {
    int value;
    int in;
    int out;
    List<Node> next;
    List<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        next = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
