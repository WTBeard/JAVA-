package zs.slg.graph;

import java.util.*;

/**
 * Dijkstra算法
 * 某个点到其他点的最短距离是多少
 */
public class Dijkstra {

    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> map = new HashMap<>();
        if (from == null) return map;
        Set<Node> set = new HashSet<>();
        map.put(from, 0);

        Node node = getMinDistanceAndUnselectedNode(map, set);
        while (node != null) {
            for (Edge edge : node.edges) {
                Integer distance = map.get(node);
                if (!map.containsKey(edge.to)) {
                    map.put(edge.to, distance + edge.weight);
                } else {
                    map.put(edge.to, Math.min(map.get(edge.to), distance + edge.weight));
                }
            }
            set.add(node);
            node = getMinDistanceAndUnselectedNode(map, set);
        }
        return map;
    }

    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> map, Set<Node> set) {
        int edge = Integer.MAX_VALUE;
        Node min = null;
        for (Map.Entry<Node, Integer> e : map.entrySet()) {
            if (e.getValue() < edge && !set.contains(e.getKey())) {
                edge = e.getValue();
                min = e.getKey();
            }
        }
        return min;
    }

    // 加强堆
    public static HashMap<Node, Integer> dijkstra2(Node from, int size) {
        HashMap<Node, Integer> map = new HashMap<>();

        NodeHeap heap = new NodeHeap(size);
        heap.addOrUpdateOrIgnore(from, 0);
        while (!heap.isEmpty()) {
            NodeRecord pop = heap.pop();
            int distance = pop.distance;
            Node node = pop.node;
            for (Edge edge : node.edges) {
                heap.addOrUpdateOrIgnore(edge.to, distance + edge.weight);
            }
            map.put(node, distance);
        }
        return map;
    }


    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        Node[] nodes;
        Map<Node, Integer> indexMap;
        Map<Node, Integer> distanceMap;
        int size;

        public NodeHeap(int size) {
            this.size = 0;
            nodes = new Node[size];
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (indexMap.containsKey(node) && indexMap.get(node) != -1) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
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
            heapIfy(0, --size);
            return nodeRecord;
        }

        public void heapIfy(int index, int size) {
            int left = 2 * index + 1;
            while (left < size) {
                int small = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[0]) ? left + 1 : left;
                small = distanceMap.get(nodes[index]) <= distanceMap.get(nodes[small]) ? index : small;
                if (small == index) break;
                swap(small,index);
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
}
