package com.kurshit.leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/*
 * Problem:
 *  Source : https://leetcode.com/problems/two-sum/
 *  Statement: 
 * 		Given an array of integers, return indices of the two numbers such that they add up to a specific target. 
 *  	You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *  
 *  	Example: 
 *  		Given nums = [2, 7, 11, 15], target = 9,
 *  
 * 			 Because nums[0] + nums[1] = 2 + 7 = 9,
 *  
 *  		return [0, 1].
 * 
 */

public class TwoSum {

	/*
	 * Approach 1: (Naive ) O(n2)
	 * 
	 * Use two for loops, and pick each element from array in outer loop and add it
	 * with every element in inner for loop and compare if it adds up to given
	 * target value.
	 * 
	 * Time : O(n2) Space : O(1)
	 * 
	 */

	public int[] findTwoSumUsingTwoLoops(int[] nums, int target) {

		int[] result = new int[2];

		for (int i = 0; i < nums.length - 1; i++) {

			for (int j = i + 1; j < nums.length; j++) {

				if (nums[i] + nums[j] == target) {
					result[0] = i;
					result[1] = j;
				}

			}

		}

		return result;

	}

	/*
	 * Approach 2 : Using HashMap O(n) *
	 * 
	 * Use hash map to store the "other pair" element of array in hashmap. Thus,
	 * while traverssing the array from left to right, we would see if (target -
	 * currentElement) is present in hashmap as a key, that would be the other pair
	 * that adds up to the target. If now present, we will add currentElement onto
	 * the map.
	 * 
	 * How would we decide result index order ?
	 * 
	 * When we check (target - currentElement) on hashmap as a key and when we do
	 * not find it, we add the currntValue on to the map.
	 * 
	 * Thus, the order of putting in values on map would be the order or array
	 * traverssed from left to right. Hence, on matching the (target -
	 * currentElement) value on map, the key present on map would be the first
	 * result index and currenetElement would be second result index.
	 * 
	 * Time Complexity : O(n) Space Complexity : O(n)
	 * 
	 */

	public int[] findTwoSumUsingHashMap(int[] nums, int target) {

		int[] result = new int[2];

		Map<Integer, Integer> arrayValuesSet = new HashMap<>();
		
		for(int curr =0; curr < nums.length; curr++) {
			
			if(arrayValuesSet.containsKey(target - nums[curr])) {
				result[0] = arrayValuesSet.get(target-nums[curr]);
				result[1] = curr;
			} else {
				arrayValuesSet.put(nums[curr], curr);
			}
		}		
		
		return result;

	}
	
	/*
	 * Approach 3 : O(n) 
	 * 	Following is alternate approach to write for loop using Java 8 streams.
	 *  
	 *  The execution time using streams takes more time than using array. 
	 * 
	 */
	
	public int[] findTwoSumUsingStreams(int[] nums, int target) {

		int[] result = new int[2];

		int size = nums.length;
		
		IntStream.range(0, size -1)
			.forEach( i -> {				
				
				IntStream.range(i+1, size)
						.filter( j -> nums[i] + nums[j] == target)
						.forEach(j ->{
							result[0] = i;
							result[1] = j;
						});
					
			});	
		
		return result;

	}
	
	/*
	 * Approach 4 : O(n) 
	 * 	Following is alternate approach to write for loop using Java 8 parellel streams.
	 *  
	 *  The execution time using parellel streams takes more time than using array but less than streams. 
	 * 
	 */
	
	public int[] findTwoSumUsingParallelStreams(int[] nums, int target) {

		int[] result = new int[2];

		int size = nums.length;
		
		IntStream.range(0, size -1)
			.parallel()
			.forEach( i -> {				
				
				IntStream.range(i+1, size)
						.parallel()
						.filter( j -> nums[i] + nums[j] == target)
						.forEach(j ->{
							result[0] = i;
							result[1] = j;
						});
					
			});	
		
		return result;

	}

	
	public static void main(String[] args) {

		TwoSum twoSum = new TwoSum();

		//int[] test1 = { 2, 7, 11, 15 };
		//int target1 = 9;
		
		int[] test1 = {3, 5, 2,-4, 8, 11 };
		int target1 = 7;

		System.out.println("\nUsing two loops: ");
		
		int[] result = twoSum.findTwoSumUsingTwoLoops(test1, target1);

		for (int i : result) {
			System.out.print(i + " ");
		}
		
		System.out.println("\nUsing HashMap : ");
		
		result = twoSum.findTwoSumUsingHashMap(test1, target1);

		for (int i : result) {
			System.out.print(i + " ");
		}
		
		System.out.println("\nUsing streams : ");
		
		result = twoSum.findTwoSumUsingStreams(test1, target1);

		for (int i : result) {
			System.out.print(i + " ");
		}
		
		System.out.println("Using parallel streams : ");
		
		result = twoSum.findTwoSumUsingParallelStreams(test1, target1);

		for (int i : result) {
			System.out.print(i + " ");
		}


	}

}
