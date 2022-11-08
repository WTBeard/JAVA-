package zs.slg.stackqueue;

import java.util.Stack;

/**
 * 实现pop，push，getMin功能的栈 O(1)
 */
public class GetMinStack {

    Stack<Integer> dataStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int newNum) {
        if (minStack.isEmpty()){
            minStack.push(newNum);
        }else {
            if (minStack.peek() > newNum){
                minStack.push(newNum);
            }else {
                minStack.push(minStack.peek());
            }
        }
        dataStack.push(newNum);
    }

    public int pop() {
        if (dataStack.isEmpty()) throw new RuntimeException("栈为空");
        minStack.pop();
        return dataStack.pop();
    }

    public int getMin() {
        if (minStack.isEmpty()) throw new RuntimeException("栈为空");
        return minStack.peek();
    }

    public static void main(String[] args) {
        GetMinStack stack1 = new GetMinStack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        GetMinStack stack2 = new GetMinStack();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }
}
