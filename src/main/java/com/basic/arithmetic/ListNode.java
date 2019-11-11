package com.basic.arithmetic;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xsy
 * @create 2019-05-14 15:08
 * @desc 链表
 **/
public class ListNode {
    private ListNode next;
    private int val;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        List<Integer> valList = new ArrayList<>();
        ListNode node = this;
        while (node != null) {
            valList.add(node.val);
            node = node.next;
        }
        return StringUtils.join(valList, ",");
    }

    //翻转链表(while循环)
    public static ListNode reverse(ListNode node) {
        ListNode theNode = node;
        ListNode prev = null;
        while (theNode != null) {
            ListNode tmp = theNode.next;
            theNode.next = prev;
            prev = theNode;
            theNode = tmp;
        }
        return prev;
    }

    //翻转链表(递归方式)
    public static ListNode reverse2(ListNode head) {
        if (head.next == null) {
            //递归出口，找到最后一个
            return head;
        }
        ListNode reverseNode = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return reverseNode;
    }

    //判断链表是否有环
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow, fast;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    //链表排序
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = middleNode(head);
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        return merge(left, right);
    }

    //取中间节点，如果中间节点数是两个取左边一个
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode merge(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                node.next = n1;
                n1 = n1.next;
            } else {
                node.next = n2;
                n2 = n2.next;
            }
            node = node.next;
        }
        if (n1 != null) {
            node.next = n1;
        } else {
            node.next = n2;
        }
        return dummy.next;
    }
}
