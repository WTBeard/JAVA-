package zs.slg.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 拓扑排序
 * 最大深度
 * https://www.lintcode.com/problem/topological-sorting
 */
public class TopologicalOrderDFS1 {

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
        DirectedGraphNode node;
        int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        if (graph == null || graph.size() == 0) return res;

        Map<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode e:graph) {
            process(e,map);
        }
        res.addAll(map.values().stream().sorted((o1, o2) -> o2.deep - o1.deep).map(o -> o.node).collect(Collectors.toList()));
        return res;
    }

    public static Record process(DirectedGraphNode node, Map<DirectedGraphNode, Record> map) {
        if (map.containsKey(node)) return map.get(node);
        int deep = 0;
        for (DirectedGraphNode e : node.neighbors) {
            deep = Math.max(deep,process(e,map).deep);
        }
        Record record = new Record(node, deep + 1);
        map.put(node,record);
        return record;
    }


}
