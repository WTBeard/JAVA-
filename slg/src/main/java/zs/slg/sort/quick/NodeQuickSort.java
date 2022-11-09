package zs.slg.sort.quick;

import zs.slg.linknode.DoubleNode;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 双链表 快排
 */
public class NodeQuickSort {

    public static DoubleNode quickSort(DoubleNode h) {
        if (h == null) return h;

        DoubleNode cur = h;
        DoubleNode end = h;
        int size = 0;
        while (cur != null) {
            end = cur;
            cur = cur.next;
            size++;
        }
        return process(h, end, size).head;
    }

    public static NodeHeadTail process(DoubleNode l, DoubleNode r, int size) {
        if (l == null) return null;
        if (l == r) return new NodeHeadTail(l, r);

        NodeInfo info = partition(l, r, size);
        NodeHeadTail left = process(info.sh, info.se, info.sSize);
        NodeHeadTail right = process(info.bh, info.be, info.bSize);
        if (left != null) {
            left.tail.next = info.eh;
            info.eh.last = left.tail;
        }
        if (right != null) {
            right.head.last = info.ee;
            info.ee.next = right.head;
        }
        return new NodeHeadTail(left != null ? left.head : info.eh, right != null ? right.tail : info.ee);
    }

    public static NodeInfo partition(DoubleNode l, DoubleNode r, int size) {
        int randomIndex = (int) (Math.random() * size);
        DoubleNode randomNode = l;
        while (randomIndex == 0) {
            randomNode = randomNode.next;
            randomIndex--;
        }

        if (randomNode == l || randomNode == r) {
            if (randomNode == l) {
                l = l.next;
                l.last = null;
            } else {
                r = r.last;
                r.next = null;
            }
        } else {
            randomNode.last.next = randomNode.next;
            randomNode.next.last = randomNode.last;
        }
        randomNode.last = null;
        randomNode.next = null;

        DoubleNode sh = null;
        DoubleNode st = null;
        int sLen = 0;
        DoubleNode eh = randomNode;
        DoubleNode et = randomNode;
        DoubleNode bh = null;
        DoubleNode bt = null;
        int bLen = 0;

        DoubleNode cur = l;
        DoubleNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            cur.last = null;
            if (cur.value == randomNode.value) {
                et.next = cur;
                cur.last = et;
                et = cur;
            } else if (cur.value < randomNode.value) {
                if (sh == null) {
                    sh = cur;
                    st = cur;
                } else {
                    st.next = cur;
                    cur.last = st;
                    st = cur;
                }
                sLen++;
            } else {
                if (bh == null) {
                    bh = cur;
                    bt = cur;
                } else {
                    bt.next = cur;
                    cur.last = bt;
                    bt = cur;
                }
                bLen++;
            }
            cur = next;
        }
        return new NodeInfo(sh, st, sLen, eh, et, bh, bt, bLen);
    }

    public static class NodeHeadTail {
        DoubleNode head;
        DoubleNode tail;

        public NodeHeadTail(DoubleNode head, DoubleNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public static class NodeInfo {
        DoubleNode sh;
        DoubleNode se;
        int sSize;
        DoubleNode eh;
        DoubleNode ee;
        DoubleNode bh;
        DoubleNode be;
        int bSize;

        public NodeInfo(DoubleNode sh, DoubleNode se, int sSize, DoubleNode eh, DoubleNode ee, DoubleNode bh, DoubleNode be, int bSize) {
            this.sh = sh;
            this.se = se;
            this.sSize = sSize;
            this.eh = eh;
            this.ee = ee;
            this.bh = bh;
            this.be = be;
            this.bSize = bSize;
        }
    }


    // 为了测试
    public static class NodeComp implements Comparator<DoubleNode> {

        @Override
        public int compare(DoubleNode o1, DoubleNode o2) {
            return o1.value - o2.value;
        }

    }

    // 为了测试
    public static DoubleNode sort(DoubleNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<DoubleNode> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head);
            head = head.next;
        }
        arr.sort(new NodeComp());
        DoubleNode h = arr.get(0);
        h.last = null;
        DoubleNode p = h;
        for (int i = 1; i < arr.size(); i++) {
            DoubleNode c = arr.get(i);
            p.next = c;
            c.last = p;
            c.next = null;
            p = c;
        }
        return h;
    }

    // 为了测试
    public static DoubleNode generateRandomDoubleLinkedList(int n, int v) {
        if (n == 0) {
            return null;
        }
        DoubleNode[] arr = new DoubleNode[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new DoubleNode((int) (Math.random() * v));
        }
        DoubleNode head = arr[0];
        DoubleNode pre = head;
        for (int i = 1; i < n; i++) {
            pre.next = arr[i];
            arr[i].last = pre;
            pre = arr[i];
        }
        return head;
    }

    // 为了测试
    public static DoubleNode cloneDoubleLinkedList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        DoubleNode h = new DoubleNode(head.value);
        DoubleNode p = h;
        head = head.next;
        while (head != null) {
            DoubleNode c = new DoubleNode(head.value);
            p.next = c;
            c.last = p;
            p = c;
            head = head.next;
        }
        return h;
    }

    // 为了测试
    public static boolean equal(DoubleNode h1, DoubleNode h2) {
        return doubleLinkedListToString(h1).equals(doubleLinkedListToString(h2));
    }

    // 为了测试
    public static String doubleLinkedListToString(DoubleNode head) {
        DoubleNode cur = head;
        DoubleNode end = null;
        StringBuilder builder = new StringBuilder();
        while (cur != null) {
            builder.append(cur.value + " ");
            end = cur;
            cur = cur.next;
        }
        builder.append("| ");
        while (end != null) {
            builder.append(end.value + " ");
            end = end.last;
        }
        return builder.toString();
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 500;
        int V = 500;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * N);
            DoubleNode head1 = generateRandomDoubleLinkedList(size, V);
            DoubleNode head2 = cloneDoubleLinkedList(head1);
            DoubleNode sort1 = quickSort(head1);
            DoubleNode sort2 = sort(head2);
            if (!equal(sort1, sort2)) {
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
