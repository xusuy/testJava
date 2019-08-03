package com.basic.arithmetic;

import org.junit.Test;

/**
 * @author xsy
 * @create 2019-05-14 15:29
 * @desc 链表测试
 **/
public class ListNodeTest {
    @Test
    public void test1() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        //System.out.println("原链表：" + listNode1);
        //翻转
        //System.out.println("翻转后1：" + ListNode.reverse(listNode1));
        //System.out.println("翻转后2：" + ListNode.reverse2(listNode1));
        //判断链表是否有环
        //listNode3.setNext(listNode1);
        System.out.println("判断链表是否有环：" + ListNode.hasCycle(listNode1));
        //链表排序
        ListNode listNode4 = new ListNode(4);
        listNode4.setNext(listNode1);
        System.out.println("链表排序前：" + listNode4);
        System.out.println("链表排序后：" + ListNode.sortList(listNode4));
    }
}
