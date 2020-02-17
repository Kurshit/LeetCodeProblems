package com.kurshit.leetcode.easy;

public class ReverseInteger {

	public int reverse(int x) {

		int result = 0;

		int no = x;
		boolean isNegative = false;

		try {

			if (no < 0) {
				no = -no;
				isNegative = true;
			}

			while (no > 0) {

				int unitDigit = no % 10;

				result = Math.multiplyExact(result, 10) + unitDigit;

				no = no / 10;
			}

			if (result > Integer.MAX_VALUE) {
				System.out.println("over");
			}

		} catch (ArithmeticException e) {
			return 0;
		}

		if (isNegative)
			return result = -result;
		else
			return result;

	}

	public static void main(String[] args) {

		ReverseInteger rev = new ReverseInteger();

		int a = 1534236469;

		System.out.println(rev.reverse(a));

	}

}
