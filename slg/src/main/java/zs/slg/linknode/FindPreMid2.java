package zs.slg.linknode;

/**
 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 */
public class FindPreMid2 {

    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) return head; //1,2,3,4,5,6

        Node slow = head; // 1
        Node fast = head.next; // 2

        while (fast.next != null || fast.next.next != null){
            slow = slow.next; // 2 3
            fast = fast.next.next; // 4 6
        }
        return slow;
    }
}
