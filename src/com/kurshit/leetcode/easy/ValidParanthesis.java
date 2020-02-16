package com.kurshit.leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * Problem:
 *  Source : https://leetcode.com/problems/valid-parentheses/
 *  Statement: 
 * 		Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. 
 * 		
 * 		An input string is valid if: 
 * 			Open brackets must be closed by the same type of brackets.
 * 			Open brackets must be closed in the correct order.
 * 		
 * 		Note that an empty string is also considered valid.
 * 
 * 	Example 1:
 * 		Input: "()"
 * 		Output: true
 * 
 * 	Example 2:
 * 		Input: "()[]{}"
 * 		Output: true
 * 
 * 	Example 3:
 * 		Input: "(]"
 * 		Output: false
 * 
 * 	Example 4:
 * 		Input: "([)]"
 * 		Output: false
 *  
 */

public class ValidParanthesis {

	public boolean isValid(String s) {

		Map<Character, Character> bracketsList = new HashMap<>();

		bracketsList.put('}', '{');
		bracketsList.put(')', '(');
		bracketsList.put(']', '[');
		bracketsList.put('>', '<');

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			Character curr = s.charAt(i);

			if (bracketsList.containsKey(curr)) {

				Character c = stack.pop();

				if (!c.equals(bracketsList.get(curr)))
					return false;

			} else {
				stack.push(curr);
			}

		}

		return stack.isEmpty();

	}

	public static void main(String[] args) {

		ValidParanthesis paranthesis = new ValidParanthesis();

		String input = "{([)}}";

		System.out.println(paranthesis.isValid(input));

	}

}
