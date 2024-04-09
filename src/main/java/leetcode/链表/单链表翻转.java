package leetcode.链表;

import java.util.List;

public class 单链表翻转 {
    public static void main(String[] args) {
        // 创建一个链表
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        // 找到链表的中间节点
        ListNode result = reverseList(head);

        // 输出中间节点的值
        ListNode.printList(result);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }


}
