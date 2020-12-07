package leetcode.排序;

/**
 * 快速排序
 */
class FastSort {
    public static int[] sort(int[] array, int start, int end) {

        // [start,end]
        int p = end;
        int a = start;
        int b = end - 1;

        if (a > b) {
            return array;
        }
        while (b > a) {
            while (array[a] < array[p] && a < p) {
                a++;
            }
            while (array[b] > array[p] && b > a) {
                b--;
            }
            if (a < b) {
                swip(array, a, b);
            }
        }

        swip(array, a, p);
        sort(array, start, a - 1);
        sort(array, a + 1, end);
        return array;
    }

    private static void swip(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
//        int[] testArray = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};
        int[] testArray = new int[]{3, 5, 4, 1, 2, 8, 9, 7, 6};
        sort(testArray, 0, testArray.length - 1);
        for (int i : testArray) {
            System.out.println(i);
        }
    }
}
