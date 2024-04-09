package test_jvm.集合框架;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class 测试删除元素 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        for (String element : list) {
            if (element.equals("B")) {
                list.remove(element); // 尝试直接移除元素
            }
        }
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String element = iterator.next();
//            if (element.equals("C")) {
//                iterator.remove(); // 使用迭代器的remove()方法移除元素
//            }
//        }
//        for (int i = 0; i < list.size(); i++) {
//            if (i == 3) {
//                list.remove(list.get(i)); // 尝试直接移除元素
//            }
//        }
    }
}
