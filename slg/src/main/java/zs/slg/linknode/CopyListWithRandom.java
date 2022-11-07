package zs.slg.linknode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制
 * 返回复制的新链表的头节点，要求时间复杂度O(N)，额外空间复杂度O(1)
 * <p>
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null
 * <p>
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandom {

    public static class Node {
        int value;
        Node next;
        Node rand;

        Node(int val) {
            value = val;
        }
    }

    public static Node copyRandomList1(Node head) {
        if (head == null) return head;

        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        map.forEach((key, value) -> {
            value.next = map.get(key.next);
            value.rand = map.get(key.rand);
        });
        return map.get(head);
    }

    public static Node copyRandomList2(Node head) {
        if (head == null) return head;

        Node cur = head;
        Node next;
        while (cur != null) {
            Node node = new Node(cur.value);
            next = cur.next;
            cur.next = node;
            node.next = next;
            cur = next;
        }

        cur = head;
        while (cur != null) {
            cur.next.rand = cur.rand != null ? cur.rand.next : null;
            cur = cur.next.next;
        }

        cur = head;
        Node res = head.next;
        Node copy;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = cur.next;
        }
        return res;
    }

}
