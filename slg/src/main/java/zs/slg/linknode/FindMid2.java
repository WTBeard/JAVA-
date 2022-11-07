package zs.slg.linknode;

/**
 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 */
public class FindMid2 {

    public static Node midOrDownMidNode(Node head) {  // 1,2,3,4,5,6
        if (head == null || head.next == null) return head;

        Node slow = head.next; // 2
        Node fast = head.next; // 2

        while (fast.next != null && fast.next.next != null){
            slow = slow.next; // 3   4
            fast = fast.next.next; // 4   6
        }
        return slow; // 4
    }

}
