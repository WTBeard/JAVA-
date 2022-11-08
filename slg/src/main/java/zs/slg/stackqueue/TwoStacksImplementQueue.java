package zs.slg.stackqueue;

import java.util.Stack;

/**
 * 两个栈实现队列
 */
public class TwoStacksImplementQueue {

    Stack<Integer> pushStack = new Stack<>();
    Stack<Integer> popStack = new Stack<>();

    public void pushToPop(){
        if (!popStack.isEmpty()) return;

        while (!pushStack.isEmpty()){
            popStack.push(pushStack.pop());
        }
    }

    public void add(int pushInt) {
        pushStack.push(pushInt);
        pushToPop();
    }

    public int poll() {
        pushToPop();
        if (popStack.isEmpty()) throw new RuntimeException("队列为空");
        return popStack.pop();
    }

    public int peek() {
        pushToPop();
        if (popStack.isEmpty()) throw new RuntimeException("队列为空");
        return popStack.peek();
    }

    public static void main(String[] args) {
        TwoStacksImplementQueue test = new TwoStacksImplementQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
