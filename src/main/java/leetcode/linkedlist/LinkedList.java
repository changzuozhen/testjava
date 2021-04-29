package leetcode.linkedlist;

import leetcode.commonutils.Utils;
import utils.LogUtils;

import java.io.IOException;
import java.util.ArrayList;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode revert( ListNode head){



        return head;
    }
}

class Wrapper {

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = Utils.stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static void prettyPrintLinkedList(ListNode node) {
        while (node != null && node.next != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }

        if (node != null) {
            LogUtils.d(node.val);
        } else {
            LogUtils.d("Empty leetcode.linkedlist.LinkedList");
        }
    }
}

public class LinkedList {
    public static void main(String[] args) throws IOException {
        String line;
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        while ((line = in.readLine()) != null) {
//            leetcode.linkedlist.ListNode node = leetcode.linkedlist.Wrapper.stringToListNode(line);
//            leetcode.tree.leetcode.linkedlist.Wrapper.prettyPrintLinkedList(node);
//        }
        line = "[1, 2, 3]";
        ListNode node = Wrapper.stringToListNode(line);
        Wrapper.prettyPrintLinkedList(node);
    }
}