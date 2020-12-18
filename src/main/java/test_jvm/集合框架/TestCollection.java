package test_jvm.集合框架;

import utils.LogUtils;

import java.util.*;

public class TestCollection {
    private static final String TAG = "TestCollection";

    public static void main(String[] args) {
//        testArrayList();
//        testLinkedList();
//        testStack();
//        testHashMap();
//        testTreeMap();
//        testLinkedHashMap();
//        testSet();
        testIntList();
    }

    private static void testHashMap() {
        LogUtils.w("testHashMap() called");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("c", "c1");
        hashMap.put("c", "c2");
        hashMap.put("a", "a1");
        hashMap.put("a", "a2");
        hashMap.put("d", "d2");
        hashMap.put("b", "b1");
        hashMap.put("b", "b2");
        LogUtils.d("testHashMap() called" + hashMap);
    }

    private static void testTreeMap() {
        //TreeMap是SortedMap的实现类，是一个红黑树的数据结构，每个key-value对作为红黑树的一个节点。
        // TreeMap存储key-value对时，需要根据key对节点进行排序。
        LogUtils.w("testTreeMap() called");
        TreeMap<String, String> hashMap = new TreeMap<>();
        hashMap.put("c", "c1");
        hashMap.put("c", "c2");
        hashMap.put("a", "a1");
        hashMap.put("a", "a2");
        hashMap.put("d", "d2");
        hashMap.put("b", "b1");
        hashMap.put("b", "b2");
        LogUtils.d("testTreeMap() called" + hashMap);
    }

    private static void testLinkedHashMap() {
        // LinkedHashMap使用双向链表来维护key-value对的次序（其实只需要考虑key的次序即可），
        // 该链表负责维护Map的迭代顺序，与插入顺序一致，因此性能比HashMap低，但在迭代访问Map里的全部元素时有较好的性能。
        LogUtils.w("testLinkedHashMap() called");
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("c", "c1");
        hashMap.put("c", "c2");
        hashMap.put("a", "a1");
        hashMap.put("a", "a2");
        hashMap.put("d", "d2");
        hashMap.put("b", "b1");
        hashMap.put("b", "b2");
        LogUtils.d("testLinkedHashMap() called" + hashMap);


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

    private static void testSet() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        LogUtils.d("testSet() called " + set.stream().anyMatch(integer -> integer > 3));
        set.add(3);
        LogUtils.d("testSet() called " + set.stream().anyMatch(integer -> integer > 3));
        set.add(4);
        LogUtils.d("testSet() called " + set.stream().anyMatch(integer -> integer > 3));

        LogUtils.d("testSet() called " + set);
        set.remove(4);
        LogUtils.d("testSet() called " + set);
        LogUtils.d("testSet() set.contains(4)  " + set.contains(4));

        set.add(2);
        set.add(4);
        set.add(5);
        LogUtils.d("testSet() called " + set);//[1, 2, 3, 4, 5]
        LogUtils.d("testSet() set.floor(3)  " + set.floor(3));          // 3    <=
        LogUtils.d("testSet() set.lower(3)  " + set.lower(3));          // 2    <
        LogUtils.d("testSet() set.ceiling(3)  " + set.ceiling(3));      // 3    >=
        LogUtils.d("testSet() set.higher(3)  " + set.higher(3));        // 4    >
    }

    private static void testIntList() {
        int[] testIntList = new int[10];
        int[][] testIntList2 = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        LogUtils.d("⚠️testIntList() called " + testIntList.length);
        for (int i : testIntList) {
            LogUtils.d("⚠️testIntList() called " + i);
        }
        LogUtils.d("⚠️testIntList2() called " + testIntList2.length);
        for (int[] i : testIntList2) {
            LogUtils.d("⚠️testIntList2() called " + i[0] + " " + i[1]);
        }
    }
}
