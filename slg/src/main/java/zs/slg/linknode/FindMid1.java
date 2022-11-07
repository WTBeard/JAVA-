package zs.slg.linknode;

/**
 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 */
public class FindMid1 {

    public static Node midOrUpMidNode(Node head) { //1,2,3,4,5,6
        if(head == null || head.next == null || head.next.next == null) return head;

        Node slow = head.next;   // 2
        Node fast = head.next.next; // 3

        while (fast.next != null && fast.next.next != null){
            slow = slow.next;  // 3
            fast = fast.next.next; // 5
        }

        return slow;
    }
}
