package zs.slg.linknode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交返回null 要求如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)
 */
public class FindFirstIntersectNode {

    // 额外空间复杂度请达到O(N)
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Set<Node> set = new HashSet<>();
        Node cur = head1;
        while (cur != null) {
            if (set.contains(cur)) break;
            set.add(cur);
            cur = cur.next;
        }
        cur = head2;
        while (cur != null) {
            if (set.contains(cur)) return cur;
            cur = cur.next;
        }
        return null;
    }


    public static Node getIntersectNode1(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;

        Node loop1 = getLoop(head1);
        Node loop2 = getLoop(head2);

        if (loop1 == null && loop2 == null) {
            return dealNoLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return dealLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    public static Node dealLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur = loop1.next;
        while (cur != loop1) {
            if (cur == loop2) return cur;
            cur = cur.next;
        }

        if (loop1 != loop2) return null;

        cur = head1;
        int size = 0;
        while (cur != loop1.next) {
            size++;
            cur = cur.next;
        }

        cur = head2;
        while (cur != loop2.next) {
            size--;
            cur = cur.next;
        }

        Node longNode = size > 0 ? head1 : head2;
        Node shortNode = longNode == head2 ? head1 : head2;
        size = Math.abs(size);
        while (size != 0){
            longNode = longNode.next;
            size--;
        }

        while (longNode != loop1.next && shortNode != loop1.next){
            if (longNode == shortNode) return longNode;
            longNode = longNode.next;
            shortNode = shortNode.next;
        }
        return null;
    }

    public static Node dealNoLoop(Node head1, Node head2) {
        int size = 0;
        Node cur = head1;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        cur = head2;
        while (cur != null) {
            size--;
            cur = cur.next;
        }
        Node longNode = size > 0 ? head1 : head2;
        Node shortNode = longNode == head1 ? head2 : head1;
        size = Math.abs(size);
        while (size != 0) {
            longNode = longNode.next;
            size--;
        }
        while (longNode != null && shortNode != null) {
            if (longNode == shortNode) return shortNode;
            longNode = longNode.next;
            shortNode = shortNode.next;
        }
        return null;
    }


    public static Node getLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) return null;

        Node slow = head.next;
        Node fast = head.next.next;

        while (fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null || fast.next == null) return null;

        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode1(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode1(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode1(head1, head2).value);

    }
}
