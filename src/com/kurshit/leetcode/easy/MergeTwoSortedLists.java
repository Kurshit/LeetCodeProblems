package com.kurshit.leetcode.easy;

/*
 * Problem:
 *  Source : https://leetcode.com/problems/merge-two-sorted-lists/submissions/
 *  Statement: 
 * 		Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists. 
 *  	   
 *  	Example: 
 *  		Input: 1->2->4, 1->3->4
 *    		
 *    		Output: 1->1->2->3->4->4  
 * 			
 * 
 */

public class MergeTwoSortedLists {
	
	public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
	}
	
	/*
	 * Approach : O(n+m)
	 * 
	 * 
	 */
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    
		if(l1 == null)
			return l2;
		
		if(l2 == null)
			return l1;
		
		ListNode dummyNode = new ListNode(-1);
		
		ListNode ptr = dummyNode;
		
		while(true) {
			
			if( l1 == null) {
				ptr.next = l2;
				break;
			}
			
			if( l2 == null) {
				ptr.next = l1;
				break;
			}
			
			if(l1.val <= l2.val) {
				ptr.next = l1;
				l1 = l1.next;
				
			} else {
				ptr.next = l2;
				l2 = l2.next;
			}
			
			ptr = ptr.next;
			
		}
		
		return dummyNode.next;
	}


}
