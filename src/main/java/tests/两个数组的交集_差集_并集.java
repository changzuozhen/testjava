package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用最少循环求两个数组的交集、差集、并集
 */
public class 两个数组的交集_差集_并集 {

    public static void main(String[] args) {
        Integer[] m = {1, 2, 3, 4, 5};
        Integer[] n = {3, 4, 6};

        System.out.println("");
        System.out.println("----------并集------------");
        Integer[] b = getB(m, n);
        for (Integer i : b) {
            System.out.print(i);
            System.out.print(' ');
        }

        System.out.println("");
        System.out.println("----------交集------------");
        Integer[] j = getJ(m, n);
        for (Integer i : j) {
            System.out.print(i);
            System.out.print(' ');
        }

        System.out.println("");
        System.out.println("----------差集------------");
        Integer[] c = getC(m, n);
        for (Integer i : c) {
            System.out.print(i);
            System.out.print(' ');
        }
    }

    /**
     * 求并集
     *
     * @param m
     * @param n
     * @return
     */
    private static Integer[] getB(Integer[] m, Integer[] n) {
        // 将数组转换为set集合
        Set<Integer> set1 = new HashSet<Integer>(Arrays.asList(m));
        Set<Integer> set2 = new HashSet<Integer>(Arrays.asList(n));

        // 合并两个集合
        set1.addAll(set2);

        Integer[] arr = {};
        return set1.toArray(arr);
    }

    /**
     * 求交集
     *
     * @param m
     * @param n
     * @return
     */
    private static Integer[] getJ(Integer[] m, Integer[] n) {
        List<Integer> rs = new ArrayList<Integer>();

        // 将较长的数组转换为set
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(m.length > n.length ? m : n));

        // 遍历较短的数组，实现最少循环
        for (Integer i : m.length > n.length ? n : m) {
            if (set.contains(i)) {
                rs.add(i);
            }
        }

        Integer[] arr = {};
        return rs.toArray(arr);
    }

    /**
     * 求差集
     *
     * @param m
     * @param n
     * @return
     */
    private static Integer[] getC(Integer[] m, Integer[] n) {
        // 将较长的数组转换为set
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(m.length > n.length ? m : n));

        // 遍历较短的数组，实现最少循环
        for (Integer i : m.length > n.length ? n : m) {
            // 如果集合里有相同的就删掉，如果没有就将值添加到集合
            if (set.contains(i)) {
                set.remove(i);
            } else {
                set.add(i);
            }
        }

        Integer[] arr = {};
        return set.toArray(arr);
    }

}
//————————————————
//        版权声明：本文为CSDN博主「Mr_EvanChen」的原创文章，遵循CC4.0BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/Mr_EvanChen/article/details/82792933