package leetcode.top;

/**
 * https://leetcode.cn/problems/add-two-numbers/description/
 * 2. 两数相加
 * 中等
 * 相关标签
 * 相关企业
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */
public class AddTwoNumbersSolution {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode();
            ListNode cur = head;
            int carry = 0;
            int sum = 0;
            int val1 = 0;
            int val2 = 0;
            int val = 0;
            while (l1 != null || l2 != null) {
                val1 = l1 == null ? 0 : l1.val;
                val2 = l2 == null ? 0 : l2.val;
                sum = val1 + val2 + carry;
                carry = sum / 10;
                val = sum % 10;
                cur.next = new ListNode(val);
                cur = cur.next;
                l1 = l1 == null ? null : l1.next;
                l2 = l2 == null ? null : l2.next;
            }
            if (carry != 0) {
                cur.next = new ListNode(carry);
            }
            return head.next;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
            ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
            ListNode result = solution.addTwoNumbers(l1, l2);
            printList(result); // Expected output: 7 -> 0 -> 8

            l1 = new ListNode(0);
            l2 = new ListNode(0);
            result = solution.addTwoNumbers(l1, l2);
            printList(result); // Expected output: 0

            l1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
            l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
            result = solution.addTwoNumbers(l1, l2);
            printList(result); // Expected output: 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1
        }

        private static void printList(ListNode node) {
            while (node != null) {
                System.out.print(node.val + " -> ");
                node = node.next;
            }
            System.out.println("null");
        }
    }

}