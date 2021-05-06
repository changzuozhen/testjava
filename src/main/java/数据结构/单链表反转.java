package 数据结构;


import utils.LogUtils;

/**
 * 单链表反转详解（4种算法实现）
 * http://c.biancheng.net/view/8105.html
 */
public class 单链表反转 {
    public static void main(String[] args) {

        ListNode head = new ListNode(0);
        ListNode tmp = head;
        for (int i = 1; i < 10; i++) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        LogUtils.d("********");
        printList(head);

        LogUtils.d("****迭代反转法****");
        head = iteration_reverse(head);
        printList(head);

        LogUtils.d("****递归反转链表****");
        head = recursive_reverse(head);
        printList(head);

        LogUtils.d("****头插法反转链表****");
        head = head_reverse(head);
        printList(head);

        LogUtils.d("****就地逆置法反转链表****");
        head = local_reverse(head);
        printList(head);
    }

    private static void printList(ListNode head) {
        ListNode tmp = head;
        while (tmp != null) {
            LogUtils.d("" + tmp.val);
            tmp = tmp.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public ListNode revert(ListNode head) {


            return head;
        }
    }

    //迭代反转法，head 为无头节点链表的头指针
    static ListNode iteration_reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode beg = null;
            ListNode mid = head;
            ListNode end = head.next;
            //一直遍历
            while (true) {
                //修改 mid 所指节点的指向
                mid.next = beg;
                //此时判断 end 是否为 null，如果成立则退出循环
                if (end == null) {
                    break;
                }
                //整体向后移动 3 个指针
                beg = mid;
                mid = end;
                end = end.next;
            }
            //最后修改 head 头指针的指向
            head = mid;
            return head;
        }
    }

    // 递归反转链表
    static ListNode recursive_reverse(ListNode head) {
        //递归的出口
        if (head == null || head.next == null)     // 空链或只有一个结点，直接返回头指针
        {
            return head;
        } else {
            //一直递归，找到链表中最后一个节点
            ListNode new_head = recursive_reverse(head.next);
            //当逐层退出时，new_head 的指向都不变，一直指向原链表中最后一个节点；
            //递归每退出一层，函数中 head 指针的指向都会发生改变，都指向上一个节点。
            //每退出一层，都需要改变 head.next 节点指针域的指向，同时令 head 所指节点的指针域为 null。
            head.next.next = head;
            head.next = null;
            //每一层递归结束，都要将新的头指针返回给上一层。由此，即可保证整个递归过程中，能够一直找得到新链表的表头。
            return new_head;
        }
    }

    // 头插法反转链表
    static ListNode head_reverse(ListNode head) {
        ListNode new_head = null;
        ListNode temp = null;
        if (head == null || head.next == null) {
            return head;
        }
        while (head != null) {
            temp = head;
            //将 temp 从 head 中摘除
            head = head.next;
            //将 temp 插入到 new_head 的头部
            temp.next = new_head;
            new_head = temp;
        }
        return new_head;
    }

    // 就地逆置法反转链表
    static ListNode local_reverse(ListNode head) {
        ListNode beg = null;
        ListNode end = null;
        if (head == null || head.next == null) {
            return head;
        }
        beg = head;
        end = head.next;
        while (end != null) {
            //将 end 从链表中摘除
            beg.next = end.next;
            //将 end 移动至链表头
            end.next = head;
            head = end;
            //调整 end 的指向，另其指向 beg 后的一个节点，为反转下一个节点做准备
            end = beg.next;
        }
        return head;
    }


}
