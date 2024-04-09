package leetcode.链表;

public class 删除倒数n节点 {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // 它可以删除链表的倒数第n个节点。思路是使用两个指针，第一个指针先移n+1步，然后两个指针同时移动，直到第一个指针到达链表末尾。此时，第二个指针指向的就是倒数第n个节点。
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
    public static void main(String[] args) {
        // 构建链表 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // 删除倒数第2个节点
        head = removeNthFromEnd(head, 2);

        // 打印结果
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
