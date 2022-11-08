package zs.slg.stackqueue;

public class DoubleEndsQueueToStackAndQueue<T> {

    private Node<T> head;
    private Node<T> tail;

    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    public void addFromHead(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.last = node;
            node.next = head;
            head = node;
        }
    }

    public void addFromBottom(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.last = tail;
            tail = node;
        }
    }

    public T popFromHead() {
        if (head == null) return null;
        Node<T> res = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.last = null;
            res.next = null;
        }
        return res.value;
    }

    public T popFromBottom() {
        if (head == null) return null;
        Node<T> res = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.last;
            tail.next = null;
            res.last = null;
        }
        return res.value;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
