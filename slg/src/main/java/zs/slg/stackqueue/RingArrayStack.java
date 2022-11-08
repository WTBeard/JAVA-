package zs.slg.stackqueue;

/**
 * 用环形数组实现栈
 * 环形数组只需要加个指针就行
 */
public class RingArrayStack {

    int[] arr;
    final int limit;
    int index;

    public RingArrayStack(int size){
        arr = new int[size];
        index = -1;
        limit = size;
    }

    public void push(int v) {
        if (++index >= arr.length) throw new RuntimeException("栈已满");
        arr[index] = v;
    }

    public int pop() {
        if (index == -1) throw new RuntimeException("栈为空");
        return arr[index--];
    }

    public boolean isEmpty() {
        return index == -1;
    }
}
