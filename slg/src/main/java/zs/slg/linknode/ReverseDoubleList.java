package zs.slg.linknode;

import java.util.ArrayList;
import java.util.List;

/**
 * 翻转 双链表
 */
public class ReverseDoubleList {

    public static DoubleNode reverseList(DoubleNode head) {
        if (head == null) return head;

        DoubleNode pre = null;
        DoubleNode cur = head;
        DoubleNode next;
        while (cur != null){
            next = cur.next;
            cur.next = pre;
            cur.last = next;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
        DoubleNode end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (Integer integer : origin) {
            if (!integer.equals(end.value)) {
                return false;
            }
            end = end.last;
        }
        return true;
    }

    public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    public static void main(String[] args) {
        int times = 500;
        int length = 500;
        int value = 100;

        boolean success;
        for (int i = 0; i < times; i++) {
            DoubleNode doubleNode = generateDoubleNode(length, value);

            success = checkDoubleListReverse(getDoubleListOriginOrder(doubleNode), reverseList(doubleNode));
            if (!success) {
                System.out.println(">>>>> error <<<<");
                break;
            }
        }
        System.out.println(">>>>> success <<<<");
    }

    public static DoubleNode generateDoubleNode(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
            size--;
        }
        return head;
    }
}
