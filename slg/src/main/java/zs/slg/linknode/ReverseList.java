package zs.slg.linknode;

import java.util.Stack;

/**
 * 翻转单链表
 */
public class ReverseList {

    public static Node reverseList(Node head) {
        if (head == null) return head;

        Node per = null;
        Node cur = head;
        Node next;
        while (cur != null){
            next = cur.next;
            cur.next = per;
            per = cur;
            cur = next;
        }
        return per;
    }

    public static void main(String[] args) {
        int times = 500;
        int length = 5;
        int value = 100;

        boolean success = true;
        for (int i = 0; i < times; i++) {
            Node node = generateNode(length, value);

            Node res1 = comparator(node);

            Node res2 = reverseList(node);

            while (res1 != null && res2 != null){
                if (res1.value != res2.value){
                    success = false;
                    break;
                }
                res1 = res1.next;
                res2 = res2.next;
            }
            if (!success) {
                System.out.println(">>>>> error <<<<");
                break;
            }
        }
        if (success){
            System.out.println(">>>>> success <<<<");
        }
    }

    public static Node comparator(Node head) {
        if (head == null) return head;
        Stack<Integer> nodes = new Stack<>();
        Node current = head;
        while (current != null) {
            nodes.push(current.value);
            current = current.next;
        }

        Node res = new Node(0);
        Node cu = res;
        while (!nodes.isEmpty()) {
            cu.next = new Node(nodes.pop());
            cu = cu.next;
        }
        return res.next;
    }

    public static Node generateNode(int length, int value) {
        length = (int) ((length + 1) * Math.random());
        length = length == 0 ? 1 : length;
        Node node = new Node(0);
        Node current = node;
        while (length > 0) {
            current.next = new Node((int) ((value + 1) * Math.random()) - (int) ((value) * Math.random()));
            current = current.next;
            length--;
        }
        return node.next;
    }

    public static void print(Node head) {
        if (head == null) return;
        while (head != null){
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
}
