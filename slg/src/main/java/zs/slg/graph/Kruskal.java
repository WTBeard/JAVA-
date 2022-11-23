package zs.slg.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  最小生成树
 *  实现Kruskal算法
 *  所有点都连通的情况下 权重和最小
 */
public class Kruskal {

    public static Set<Edge> kruskalMST(Graph graph) {
        Set<Edge> res = new HashSet<>();
        UnionFind unionFind = new UnionFind(graph.nodes.values());
        List<Edge> edges = graph.edges.stream().sorted(Comparator.comparingInt(o -> o.weight)).collect(Collectors.toList());
        for (Edge edge : edges) {
            if (!unionFind.isSameSet(edge.from, edge.to)){
                res.add(edge);
                unionFind.union(edge.from,edge.to);
            }
        }
        return res;
    }

    public static class UnionFind{
        Map<Node,Node> parentMap;
        Map<Node,Integer> sizes;

        public UnionFind(Collection<Node> nodes){
            parentMap = new HashMap<>();
            sizes = new HashMap<>();

            for (Node node:nodes) {
                parentMap.put(node,node);
                sizes.put(node,1);
            }
        }

        public Node findParent(Node node){
            Stack<Node> stack = new Stack<>();
            Node cur = node;
            while (cur != parentMap.get(cur)){
                stack.add(cur);
                cur = parentMap.get(cur);
            }

            while (!stack.isEmpty()){
                parentMap.put(stack.pop(),cur);
            }
            return cur;
        }

        public boolean isSameSet(Node a, Node b){
            return findParent(a) == findParent(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aNode = findParent(a);
            Node bNode = findParent(b);
            if (aNode == bNode) return;

            int aSize = sizes.get(aNode);
            int bSize = sizes.get(bNode);
            Node big = aSize >= bSize ? aNode:bNode;
            Node small = big == aNode ? bNode : aNode;

            parentMap.put(small,big);
            sizes.put(big,sizes.get(big) + sizes.get(small));
            sizes.remove(small);
        }

    }
}
