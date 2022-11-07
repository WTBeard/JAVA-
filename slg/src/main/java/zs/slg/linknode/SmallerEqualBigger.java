package zs.slg.linknode;

/**
 * 给定一个单链表的头节点head，给定一个整数n，将链表按n划分成左边<n、中间==n、右边>n
 */
public class SmallerEqualBigger {

    public static Node listPartition1(Node head, int pivot) {
        if (head == null) return head;

        int size = getNodeSize(head);
        Node[] nodes = convertArr(head, size);

        partition(nodes, pivot);

        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        return nodes[0];
    }

    public static void partition(Node[] arr, int pivot) {
        if (arr == null || arr.length < 2) return;

        int ls = -1;
        int rs = arr.length;
        int cur = 0;
        while (cur < rs) {
            if (arr[cur].value < pivot) {
                swap(arr, ++ls, cur++);
            } else if (arr[cur].value > pivot) {
                swap(arr, --rs, cur);
            } else {
                cur++;
            }
        }
    }

    public static void swap(Node[] arr, int i, int j) {
        if (i == j) return;
        Node tmp1 = arr[i];
        Node tmp2 = arr[j];
        arr[i] = tmp2;
        arr[j] = tmp1;
    }

    public static int getNodeSize(Node head) {
        int size = 0;
        Node cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        return size;
    }

    public static Node[] convertArr(Node head, int size) {
        Node[] arr = new Node[size];
        int i = 0;
        Node cur = head;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            arr[i++] = cur;
            cur = next;
        }
        return arr;
    }

    public static Node listPartition2(Node head, int pivot) {
        if (head == null) return head;
        NodeInfo info = nodePartition(head, pivot);
        return info.getHead().head;
    }

    public static NodeInfo nodePartition(Node head, int pivot) {
        Node cur = head;
        Node sh = null;
        Node se = null;
        Node eh = null;
        Node ee = null;
        Node bh = null;
        Node be = null;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            if (cur.value < pivot) {
                if (sh == null) {
                    sh = cur;
                } else {
                    se.next = cur;
                }
                se = cur;
            } else if (cur.value > pivot) {
                if (bh == null) {
                    bh = cur;
                } else {
                    be.next = cur;
                }
                be = cur;
            } else {
                if (eh == null) {
                    eh = cur;
                } else {
                    ee.next = cur;
                }
                ee = cur;
            }
            cur = next;
        }
        return new NodeInfo(sh, se, eh, ee, bh, be);
    }

    public static class NodeInfo {
        Node sh;
        Node se;
        Node eh;
        Node ee;
        Node bh;
        Node be;

        Node head;

        public NodeInfo(Node sh, Node se, Node eh, Node ee, Node bh, Node be) {
            this.sh = sh;
            this.se = se;
            this.eh = eh;
            this.ee = ee;
            this.bh = bh;
            this.be = be;
        }

        public NodeInfo getHead(){
            if (sh != null) {
                head = sh;
                if (eh != null){
                    se.next = eh;
                    if (bh != null){
                        ee.next = bh;
                    }
                }else {
                    if (bh != null){
                        se.next = bh;
                    }
                }
            }else if (eh != null){
                head = eh;
                if (bh != null){
                    ee.next = bh;
                }
            }else if (bh != null){
                head = bh;
            }
            return this;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//        head1 = listPartition1(head1, 5);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);
    }


    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
}
