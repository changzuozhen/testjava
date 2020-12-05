package test_jvm.集合框架;

import utils.LogUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class TestCollection {
    public static void main(String[] args) {
        testArrayList();
        testLinkedList();
        testStack();
    }

    static void testArrayList() {
        LogUtils.w("testArrayList() called");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        LogUtils.d("list:" + arrayList);
        LogUtils.d("list size():" + arrayList.size());
        LogUtils.d("list remove(0):" + arrayList.remove(0));
        LogUtils.d("list size():" + arrayList.size());
        LogUtils.d("list:" + arrayList);
    }

    static void testLinkedList() {
        LogUtils.w("testLinkedList() called");
// LinkedList
// LinkedList是List接口的另一个实现，除了可以根据索引访问集合元素外，LinkedList还实现了Deque接口，可以当作双端队列来使用，也就是说，既可以当作“栈”使用，又可以当作队列使用。
// LinkedList的实现机制与ArrayList的实现机制完全不同，ArrayLiat内部以数组的形式保存集合的元素，所以随机访问集合元素有较好的性能；LinkedList内部以链表的形式保存集合中的元素，所以随机访问集合中的元素性能较差，但在插入删除元素时有较好的性能。
        LinkedList<String> linkedList = new LinkedList<>();
        LogUtils.d("list isEmpty:" + linkedList.isEmpty());
        linkedList.add("a");
        linkedList.add("b");
        LogUtils.d("list:" + linkedList);
        LogUtils.d("list isEmpty:" + linkedList.isEmpty());

        linkedList.addFirst("first1");
        linkedList.addFirst("first2");
        linkedList.addLast("last1");
        linkedList.addLast("last2");
        LogUtils.d("list:" + linkedList);


        LogUtils.i("peek - Retrieves, but does not remove, the head (first element) of this list.");
        LogUtils.d("list:" + linkedList.peek());


        LogUtils.i("peekFirst - Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.");
        LogUtils.d("list:" + linkedList.peekFirst());

        LogUtils.i("peekLast - Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.");
        LogUtils.d("list:" + linkedList.peekLast());


        LogUtils.i("pollFirst - Retrieves and removes the first element of this list, or returns null if this list is empty.");
        LogUtils.d("list:" + linkedList.pollFirst());
        LogUtils.d("list:" + linkedList);


        LogUtils.i("pollLast - Retrieves and removes the last element of this list, or returns null if this list is empty.");
        LogUtils.d("list:" + linkedList.pollLast());
        LogUtils.d("list:" + linkedList);


//        LogUtils.d("");
//        LogUtils.d("");
//        LogUtils.d("list:" + linkedList);
//        LogUtils.d("list:" + linkedList);
//        LogUtils.d("list:" + linkedList);
    }

    static void testStack() {
        LogUtils.w("testStack() called");
        Stack<String> stack = new Stack<>();
        LogUtils.d("stack: empty:" + stack.empty());
        LogUtils.d("stack: isEmpty:" + stack.isEmpty());
        stack.add("a");
        stack.add("b");
        LogUtils.d("stack:" + stack);
        LogUtils.d("stack: isEmpty:" + stack.isEmpty());

        stack.push("push1");
        stack.push("push2");
        LogUtils.d("stack:" + stack);


        LogUtils.i("peek - Looks at the object at the top of this stack without removing it from the stack.");
        LogUtils.d("stack:" + stack.peek());


        LogUtils.i("pop - Removes the object at the top of this stack and returns that object as the value of this function.");
        LogUtils.d("stack:" + stack.pop());


//        LogUtils.d("");
//        LogUtils.d("");
        LogUtils.d("stack:" + stack);
//        LogUtils.d("stack:" + stack);
//        LogUtils.d("stack:" + stack);
    }
}
