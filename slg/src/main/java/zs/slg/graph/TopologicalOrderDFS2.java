package zs.slg.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 拓扑排序
 * 点次
 * https://www.lintcode.com/problem/topological-sorting
 */
public class TopologicalOrderDFS2 {

    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record {
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode n, long o) {
            node = n;
            nodes = o;
        }
    }


    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        if (graph == null || graph.size() == 0) return res;

        Map<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode e:graph) {
            process(e,map);
        }
        res.addAll(map.values().stream().sorted((o1, o2) -> Math.toIntExact(o2.nodes - o1.nodes)).map(o -> o.node).collect(Collectors.toList()));
        return res;
    }


    public static Record process(DirectedGraphNode node, Map<DirectedGraphNode, Record> map) {
        if (map.containsKey(node)) return map.get(node);

        long nodes = 0;
        for (DirectedGraphNode e:node.neighbors) {
            nodes += process(e,map).nodes;
        }
        Record record = new Record(node, nodes + 1);
        map.put(node,record);
        return record;
    }
}
