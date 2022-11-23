package zs.slg.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 网络延迟时间
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * <p>
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * <p>
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * <p>
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 * <p>
 * 测试链接 : https://leetcode.com/problems/network-delay-time
 */
public class NetworkDelayTime {

    public static int networkDelayTime1(int[][] times, int n, int k) {
        if (times == null || times.length == 0) return -1;
        Graph graph = convertGraph(times);
        if (!graph.nodes.containsKey(n)) return -1;

        HashSet<Node> set = new HashSet<>();
        HashMap<Node,Integer> map = new HashMap<>();
        map.put(graph.nodes.get(k),0);
        Node node = getMidNode(map, set);
        int res = 0;
        while (node != null){
            Integer distance = map.get(node);
            for (Edge edge:node.edges) {
                if (!map.containsKey(edge.to)){
                    map.put(edge.to, distance + edge.weight);
                }else {
                    map.put(edge.to,Math.min(distance + edge.weight,map.get(edge.to)));
                }
            }
            set.add(node);
            node = getMidNode(map, set);
            res = Math.max(res, distance);
        }
        return res;
    }

    public static Node getMidNode(HashMap<Node,Integer> map,HashSet<Node> set){
        Node res = null;
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Node,Integer> e:map.entrySet()){
            if (!set.contains(e.getKey()) && e.getValue() < min){
                min = e.getValue();
                res = e.getKey();
            }
        }
        return res;
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        if (times == null || times.length == 0) return -1;
        Graph graph = convertGraph(times);
        if (!graph.nodes.containsKey(n)) return -1;
        NodeHeap nodeHeap = new NodeHeap(graph.nodes.size());
        nodeHeap.addOrUpdate(graph.nodes.get(k), 0);
        int res = 0;
        while (!nodeHeap.isEmpty()){
            NodeRecord nodeRecord = nodeHeap.pop();
            for (Edge edge:nodeRecord.node.edges) {
                nodeHeap.addOrUpdate(edge.to, nodeRecord.distance + edge.weight);
            }
            res = Math.max(res,nodeRecord.distance);
        }
        return res;
    }

    private static class NodeRecord {
        Node node;
        int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private static class NodeHeap {
        Node[] nodes;
        Map<Node, Integer> indexMap;
        Map<Node, Integer> distanceMap;
        int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            this.size = 0;
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addOrUpdate(Node node, int distance) {
            if (indexMap.containsKey(node) && indexMap.get(node) != -1) {
                distanceMap.put(node, Math.min(distance, distanceMap.get(node)));
                heapInsert(indexMap.get(node));
            }

            if (!indexMap.containsKey(node)) {
                nodes[size] = node;
                indexMap.put(node, size);
                distanceMap.put(node, distance);
                heapInsert(size++);
            }
        }

        public void heapInsert(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            indexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapIfy(0, --size);
            return nodeRecord;
        }

        public void heapIfy(int index, int size) {
            int left = 2 * index + 1;
            while (left < size) {
                int small = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
                small = distanceMap.get(nodes[index]) <= distanceMap.get(nodes[small]) ? index : small;
                if (small == index) break;
                swap(small, index);
                index = small;
                left = 2 * index + 1;
            }
        }

        public void swap(int i, int j) {
            if (i == j) return;
            Node in = nodes[i];
            Node jn = nodes[j];
            nodes[i] = jn;
            nodes[j] = in;

            indexMap.put(in, j);
            indexMap.put(jn, i);
        }
    }

    public static Graph convertGraph(int[][] times) {
        Graph graph = new Graph();
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int weight = time[2];

            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
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

    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int i = networkDelayTime(times, 4, 2);
        System.out.println(i);
        int j = networkDelayTime1(times, 4, 2);
        System.out.println(j);

    }
}
