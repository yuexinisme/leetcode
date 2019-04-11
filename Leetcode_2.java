package com.nick.imdb;

/**
 * @author Nick Yuan
 * @date 2019/4/11
 * @mood shitty
 */
public class Leetcode_2 {
    /**
     * You are given two non-empty linked lists representing two non-negative integers. T
     * he digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     * Solution: Since the digits are stored backwards, they can be added digit by digit from
     * the two starting nodes . The scenario when the added digit may exceed 9 must be monitored,
     * as well as some corner cases, such as when the last digit exceeds 9, meaning that an additional
     * digit must be appended. I find it weird that Leetcode labelled this as "Medium" when there are
     * multiple questions labelled "easy" are literally ten times harder.
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean flag=false;
        ListNode node=new ListNode(0);
        ListNode temp=node; //save the starting node to return, as the reference is changed later.
        while(l1!=null||l2!=null){
            node.val=(l1==null?0:l1.val)+(l2==null?0:l2.val)+(flag?1:0);
            if(node.val>9){
                node.val-=10;
                flag=true;
            }else flag=false;
            l1=(l1==null?null:l1.next);
            l2=(l2==null?null:l2.next);
            if(l1==null&&l2==null){
                if(flag){
                    node.next=new ListNode(1);
                }
                break;
            }
            ListNode next=new ListNode(0);
            node.next=next;
            node=next;
        }
        return temp;
    }
}
   class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
   }