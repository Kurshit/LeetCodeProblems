package com.kurshit.leetcode.easy;

public class RemoveDuplicatesFromSortedArray {
	
	public int removeDuplicates(int[] nums) {
		
		int lengthCounter = 0;
		
		int temp = 0;
		
		for(int i=0; i< nums.length; i++) {
			
			if(nums[temp] == nums[i])
				continue;
			else {
				temp++;
				nums[temp] = nums[i];
				//temp++;
				lengthCounter =  lengthCounter + 1;;
				
			}
			
		}	
		
		return lengthCounter+1;
	}

	public static void main(String[] args) {
		
		RemoveDuplicatesFromSortedArray rdfa = new RemoveDuplicatesFromSortedArray();
		
		//int[] nums = {1,1,2};
		int[] nums = {0,0,1,1,1,2,2,3,3,4};
		
		System.out.println("Length : " +rdfa.removeDuplicates(nums));
		

	}

}
