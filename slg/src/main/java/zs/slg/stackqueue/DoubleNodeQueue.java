package zs.slg.stackqueue;

/**
 * 用双链表实现队列
 */
public class DoubleNodeQueue<T> {

    private final DoubleEndsQueueToStackAndQueue<T> stackAndQueue;

    public DoubleNodeQueue(){
        stackAndQueue = new DoubleEndsQueueToStackAndQueue<T>();
    }

    public void push(T value) {
        stackAndQueue.addFromHead(value);
    }

    public T poll() {
        return stackAndQueue.popFromBottom();
    }

    public boolean isEmpty() {
        return stackAndQueue.isEmpty();
    }
}
