package zs.slg.recursive;

import java.util.Stack;

/**
 * 给定一个栈，请逆序这个栈，不能申请额外的数据结构，只能使用递归函数
 */
public class ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        int i = process(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int process(Stack<Integer> stack){
        Integer temp = stack.pop();
        if (stack.isEmpty()){
            return temp;
        }
        int res = process(stack);
        stack.push(temp); // 把每一步不是最后的 在压入
        return res;
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
