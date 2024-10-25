package leetcode.top;

/**
 * ### 8.  合并两个有序链表（Merge Two Sorted Lists）：将两个升序链表合并为一个新的升序链表并返回。
 * [题目链接](https://leetcode-cn.com/problems/merge-two-sorted-lists/)，
 * [题解链接](https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/)
 */

class MergeTwoSortedListsSolution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while ((list1 != null) || (list2 != null)) {
            if ((list2 == null) || ((list1 != null) && (list1.val <= list2.val))) {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            } else {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        return head.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public String toString() {
            ListNode node = this;
            StringBuilder sb = new StringBuilder();
            while (node != null) {
                sb.append(node.val).append(" -> ");
                node = node.next;
            }
            sb.append("null");
            return sb.toString();
        }

    }

    public static boolean equals(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1 == null && l2 == null;
    }

    public static void main(String[] args) {
        MergeTwoSortedListsSolution solution = new MergeTwoSortedListsSolution();
        runTestCases(solution);
    }

    private static void runTestCases(MergeTwoSortedListsSolution solution) {
        testCase(solution, new int[]{1, 2, 4}, new int[]{1, 3, 4}, new int[]{1, 1, 2, 3, 4, 4});
        testCase(solution, new int[]{}, new int[]{}, new int[]{});
        testCase(solution, new int[]{}, new int[]{1, 2, 3}, new int[]{1, 2, 3});
        testCase(solution, new int[]{1, 2, 3}, new int[]{}, new int[]{1, 2, 3});
        testCase(solution, new int[]{1, 3, 5}, new int[]{2, 4, 6}, new int[]{1, 2, 3, 4, 5, 6});
        testCase(solution, new int[]{1, 2, 3, 4}, new int[]{2, 3}, new int[]{1, 2, 2, 3, 3, 4});
        testCase(solution, new int[]{1, 2}, new int[]{1, 3, 4, 5}, new int[]{1, 1, 2, 3, 4, 5});
        testCase(solution, new int[]{1}, new int[]{2}, new int[]{1, 2});
        testCase(solution, new int[]{3, 2, 1}, new int[]{6, 5, 4}, new int[]{3, 2, 1, 6, 5, 4});
        testCase(solution, new int[]{1, 2, 4}, new int[]{3, 4, 5}, new int[]{1, 2, 3, 4, 4, 5});
        testCase(solution, new int[]{1, 2, 3}, new int[]{7, 8, 9}, new int[]{1, 2, 3, 7, 8, 9});
    }

    private static void testCase(MergeTwoSortedListsSolution solution, int[] arr1, int[] arr2, int[] expected) {
        ListNode list1 = createList(arr1);
        ListNode list2 = createList(arr2);
        ListNode expectedList = createList(expected);
        ListNode result = solution.mergeTwoLists(list1, list2);
        boolean res = equals(expectedList, result);
        System.out.println("Test case passed: " + res + (res ? "" : " " + result.toString()));
    }

    private static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }
}