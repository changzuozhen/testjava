package leetcode.链表;

public class 求链表的中间节点 {
    public static void main(String[] args) {
        // 创建一个链表
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        // 找到链表的中间节点
        ListNode middleNode = findMiddleNode(head);

        // 输出中间节点的值
        System.out.println("The middle node of the linked list is: " + middleNode.val);
    }

    public static ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head; // 最终n/2 节点为中心节点
//        ListNode fast = head.next; // 最终n/2 节点为中心节点

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

}
