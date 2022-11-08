package zs.slg.stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 两个队列实现栈
 */
public class TwoQueueImplementStack {

    Queue<Integer> queue = new LinkedList<>();
    Queue<Integer> help = new LinkedList<>();


    public void push(int value) {
        queue.offer(value);
    }

    public int poll() {
        if (queue.isEmpty()) throw new RuntimeException("栈为空");
        while (queue.size() != 1) {
            help.offer(queue.poll());
        }
        Integer res = queue.poll();

        Queue<Integer> tmp = queue;
        queue = help;
        help = tmp;

        return res;
    }

    public int peek() {
        if (queue.isEmpty()) throw new RuntimeException("栈为空");
        while (queue.size() != 1) {
            help.offer(queue.poll());
        }
        Integer res = queue.peek();

        Queue<Integer> tmp = queue;
        queue = help;
        help = tmp;

        return res;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueImplementStack myStack = new TwoQueueImplementStack();

        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);

        while (!myStack.isEmpty()){
            System.out.println(myStack.poll());
        }

        System.out.println("test finish!");

    }

}
