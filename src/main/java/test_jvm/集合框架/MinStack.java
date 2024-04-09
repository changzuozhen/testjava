package test_jvm.集合框架;

import java.util.Stack;

class MinStack {

    // 定义两个栈，一个用来存储元素，一个用来存储当前的最小元素
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    // 构造函数，初始化两个空栈
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    // 压入操作
    public void push(int val) {
        // 将元素压入stack
        stack.push(val);
        // 如果minStack为空或者元素小于或等于minStack的栈顶元素，则将元素也压入minStack
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    // 弹出操作
    public void pop() {
        // 如果stack为空，则直接返回
        if (stack.isEmpty()) {
            return;
        }
        // 将stack的栈顶元素弹出，并赋值给变量x
        int x = stack.pop();
        // 如果x等于minStack的栈顶元素，则将minStack的栈顶元素也弹出
        if (x == minStack.peek()) {
            minStack.pop();
        }
    }

    // 获取栈顶元素操作
    public int top() {
        // 如果stack为空，则返回-1
        if (stack.isEmpty()) {
            return -1;
        }
        // 直接返回stack的栈顶元素
        return stack.peek();
    }

    // 获取最小元素操作
    public int getMin() {
        // 如果minStack为空，则返回-1
        if (minStack.isEmpty()) {
            return -1;
        }
        // 直接返回minStack的栈顶元素
        return minStack.peek();
    }

    // 获取最小元素操作
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return "MinStack{" +
                "stack=" + stack +
                ", minStack=" + minStack +
                '}';
    }

    public static void main(String[] args) {
        // 创建一个最小栈对象
        MinStack minStack = new MinStack();
        int j = 1;
        // 压入一些元素
        for (int i = 5; i < 10; i++) {
            minStack.push(i * j);
            j = -j;
            System.out.println("getMin:" + minStack.getMin() + " top:" + minStack.top() + " status" + minStack);
        }
        for (int i = 0; i < 5; i++) {
            minStack.push(i * j);
            j = -j;
            System.out.println("getMin:" + minStack.getMin() + " top:" + minStack.top() + " status" + minStack);
        }

            System.out.println("-------");
        while (!minStack.isEmpty()) {
            System.out.println("getMin:" + minStack.getMin() + " top:" + minStack.top() + " status" + minStack);
            // 弹出一个元素
            minStack.pop();
        }
    }

}
