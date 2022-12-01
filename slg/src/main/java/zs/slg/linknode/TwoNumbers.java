package zs.slg.linknode;

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 * https://leetcode.cn/problems/add-two-numbers/?favorite=2cktkvj
 *
 */
public class TwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        ListNode finalRes = new ListNode(0);
        ListNode res = finalRes;
        int level = 0;
        while (l1 != null && l2 != null){
            int sum = l1.val + l2.val + level;
            if (sum >= 10){
                level = 1;
                sum = sum % 10;
            }else {
                level = 0;
            }
            res.next = new ListNode(sum);

            l1 = l1.next;
            l2 = l2.next;
            res = res.next;
        }
        while (l1 != null){
            if (level != 0){
                int sum = l1.val + level;
                if (sum >= 10){
                    level = 1;
                    sum = sum % 10;
                }else {
                    level = 0;
                }
                res.next = new ListNode(sum);
            }else {
                res.next = new ListNode(l1.val);
            }
            res = res.next;
            l1 = l1.next;
        }
        while (l2 != null){
            if (level != 0){
                int sum = l2.val + level;
                if (sum >= 10){
                    level = 1;
                    sum = sum % 10;
                }else {
                    level = 0;
                }
                res.next = new ListNode(sum);
            }else {
                res.next = new ListNode(l2.val);
            }
            res = res.next;
            l2 = l2.next;
        }
        if (level == 1){
            res.next = new ListNode(1);
        }
        return finalRes.next;
    }

}
