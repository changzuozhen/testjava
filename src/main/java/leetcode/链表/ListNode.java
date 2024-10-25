package leetcode.链表;

public class ListNode {
    public ListNode next;
    int val;

    public ListNode(int i) {
        val = i;
    }

    public ListNode() {

    }


    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
