package leetcode.two_sum;

import leetcode.commonutils.Utils;

import java.io.IOException;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        if (next != null) return val + " -> " + next.toString();
        return "" + val;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}

public class TwoSum {
    public static void main(String[] args) throws IOException {
        String line;
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        while ((line = in.readLine()) != null) {
//            leetcode.linkedlist.ListNode node = leetcode.linkedlist.Wrapper.stringToListNode(line);
//            leetcode.tree.leetcode.linkedlist.Wrapper.prettyPrintLinkedList(node);
//        }
        line = "[1, 2, 3]";
        int[] intlist = Utils.stringToIntegerArray(line);
        line = "[1, 2, 3]";
        int[] intlist2 = Utils.stringToIntegerArray(line);

        ListNode result = new Solution().addTwoNumbers(convert(intlist), convert(intlist2));
        System.out.println(result.toString());
    }

    private static ListNode convert(int[] intlist) {
        if (intlist.length > 0) {
            ListNode result = new ListNode(0);
            ListNode current = result;
            for (int i = 0; i < intlist.length; i++) {
                current.next = new ListNode(intlist[i]);
                current = current.next;
            }
            return result.next;
        }
        return null;
    }
}