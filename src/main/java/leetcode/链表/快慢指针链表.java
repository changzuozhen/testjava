package leetcode.链表;

/**
 * 给你一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 */
public class 快慢指针链表 {
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
        System.out.println("The middle node of the linked list is: " + middleNode);

        head.next.next.next.next.next.next = head.next;
        middleNode = detectCycle(head);

        // 输出环的入口节点的值
        System.out.println("The middle node of the linked list is: " + middleNode);
    }

    /**
     * 1. 快慢指针
     * 2. 找到链表的中间节点
     *
     * @param head
     * @return
     */
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

    /**
     * 1. **定义**: - 设链表的非环部分长度为 ( a )，环的长度为 ( b )。 - 快指针每次移动两步，慢指针每次移动一步。
     * 2. **相遇点证明**: - 假设慢指针进入环后，在环中移动了 ( d ) 步时与快指针相遇。 - 此时，快指针已经在环中走了 ( 2d ) 步，即快指针比慢指针多走了环的长度 ( b ) 的整数倍。
     * 3. **环入口证明**: - 当快慢指针相遇时，设慢指针在环中走了 ( d ) 步，快指针走了 ( 2d ) 步。 - 快指针比慢指针多走的距离是 ( 2d - d = d )，这个距离是环长度 ( b ) 的整数倍。 - 如果此时有一个新指针从链表头部开始移动，并且慢指针继续移动，那么它们都将在环的入口相遇。这是因为从链表头部到环入口的距离是 ( a )，而从相遇点回到环入口的距离也是 ( a )。
     * 4. **数学表达**: - 设慢指针走了 ( s ) 步，则快指针走了 ( 2s ) 步。 - 当它们相遇时，快指针比慢指针多走了 ( n ) 圈的环，即 ( 2s = s + nb )。 - 因此，( s = nb )。 - 如果慢指针走了 ( a + d ) 步，则 ( a + d = nb )（( d ) 是慢指针在环中走的步数）。 - 因此，( a = nb - d )。 - 这意味着从链表头部到环入口的距离 ( a ) 等于 ( nb - d )，即环的整数倍减去慢指针在环中走的步数。 通过上述证明，我们可以得出结论，使用快慢指针可以检测链表中的环，并且找到环的入口节点。这个证明基于快指针每次移动两步，慢指针每次移动一步的假设。如果快指针的速度不是慢指针的两倍，那么证明过程会有所不同，但基本原理是相同的¹²³⁴⁵.
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

}
