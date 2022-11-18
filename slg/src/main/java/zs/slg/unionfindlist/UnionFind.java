package zs.slg.unionfindlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集结构
 * @param <V>
 */
public class UnionFind<V> {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    private final Map<V, Node<V>> nodes = new HashMap<>();
    private final Map<Node<V>, Node<V>> parentNodes = new HashMap<>();
    private final Map<Node<V>, Integer> sizes = new HashMap<>();

    public UnionFind(List<V> values) {
        for (V cur : values) {
            Node<V> node = new Node<>(cur);
            nodes.put(cur, node); // a -> (a)
            parentNodes.put(node, node); // 自己指向自己
            sizes.put(node, 1); // 大小为 1
        }
    }

    public boolean isSameSet(V a, V b) {
        return findFather(nodes.get(a)) == findFather(nodes.get(b));
    }

    // 给你一个节点，请你往上到不能再往上，把代表返回
    public Node<V> findFather(Node<V> cur) {
        Stack<Node<V>> stack = new Stack<>();
        while (cur != parentNodes.get(cur)) {
            stack.push(cur);
            cur = parentNodes.get(cur);
        }

        while (!stack.isEmpty()) {
            parentNodes.put(stack.pop(), cur);
        }
        return cur;
    }

    public void union(V a, V b) {
        Node<V> an = findFather(nodes.get(a));
        Node<V> bn = findFather(nodes.get(b));
        if (an != bn) {
            Integer as = sizes.get(an);
            Integer bs = sizes.get(bn);
            Node<V> bigNode = as >= bs ? an : bn;
            Node<V> smallNode = bigNode == an ? bn : an;
            parentNodes.put(smallNode, bigNode);
            sizes.put(bigNode, sizes.get(bigNode) + sizes.get(smallNode));
            sizes.remove(smallNode);
        }
    }

    public int sets() {
        return sizes.size();
    }
}
