package zs.slg.stackqueue;

/**
 * 用双链表实现栈
 */
public class DoubleNodeStack<T> {

    private final DoubleEndsQueueToStackAndQueue<T> stackAndQueue;

    public DoubleNodeStack(){
        stackAndQueue = new DoubleEndsQueueToStackAndQueue<T>();
    }


    public void push(T value) {
        stackAndQueue.addFromHead(value);
    }

    public T poll() {
        return stackAndQueue.popFromHead();
    }

    public boolean isEmpty() {
        return stackAndQueue.isEmpty();
    }
}
