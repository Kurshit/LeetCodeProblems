package com.kurshit.leetcode.easy;

/*
 * Problem:
 *  Source : https://leetcode.com/problems/palindrome-number/
 *  Statement: 
 * 		Determine whether an integer is a palindrome. An integer is a palindrome when it reads 
 * 		the same backward as forward.
 * 
 * 	Example 1:
 * 		Input: 121
 * 		Output: true
 * 
 * 	Example 2:
 * 		Input: -121
 * 		Output: false
 * 
 *		Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * 
 */


public class Palindrome {
	
	/*
	 * Approach 1: O(n)
	 * 		For a non-negative value, we will get each digit on original integer and reverse it by using multiple of 10 logic
	 * 		just like in case of reverse-the-digit problem. THe reverse would be compared with original value.
	 */
	
	public boolean isPalindrome(int x) {
		
		int no = x;
		int reverseResult = 0;
		
		if(no< 0)
			return false;
		
		while(no > 0) {
			
			int unitDigit = no % 10;
			
			reverseResult = reverseResult * 10 + unitDigit;
			
			no = no/10;
		}
		
		return x == reverseResult;
	}
	
	public static void main(String[] args) {
		
		Palindrome p = new Palindrome();
		System.out.println(p.isPalindrome(121));
	
	}

}

