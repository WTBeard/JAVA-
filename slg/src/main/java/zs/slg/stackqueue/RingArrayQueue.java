package zs.slg.stackqueue;

/**
 * 用环形数组实现队列
 */
public class RingArrayQueue {

    int push;
    int pop;
    int size;
    int[] arr;
    final int limit;

    public RingArrayQueue(int size) {
        this.limit = size;
        arr = new int[size];
        push = 0;
        pop = 0;
    }

    public void push(int value) {
        if (size == limit) throw new RuntimeException("队列已满");
        arr[push] = value;
        size++;
        push = nextIndex(push);
    }

    public int pop() {
        if (size == 0) throw new RuntimeException("队列为空");
        int i = arr[pop];
        pop = nextIndex(pop);
        size--;
        return i;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int nextIndex(int index) {
        return index < limit - 1 ? index + 1 : 0;
    }
}
