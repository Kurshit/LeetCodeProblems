package com.kurshit.leetcode.concurrency.zeroevenodd;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOddSemaphore {

	private int n;

	Semaphore zeroSemaphore = new Semaphore(1);
	Semaphore evenSemaphore = new Semaphore(0);
	Semaphore oddSemaphore = new Semaphore(0);

	public ZeroEvenOddSemaphore(int n) {
		this.n = n;
	}

	// printNumber.accept(x) outputs "x", where x is an integer.
	public void zero(IntConsumer printNumber) throws InterruptedException {

		for (int i = 1; i <= n; i++) {
			zeroSemaphore.acquire();
			printNumber.accept(0);

			if (i % 2 == 0)
				evenSemaphore.release();
			else
				oddSemaphore.release();

		}
	}

	public void even(IntConsumer printNumber) throws InterruptedException {

		for (int i = 2; i <= n; i += 2) {
			evenSemaphore.acquire();
			printNumber.accept(i);
			zeroSemaphore.release();
		}

	}

	public void odd(IntConsumer printNumber) throws InterruptedException {
		for (int i = 1; i <= n; i += 2) {
			oddSemaphore.acquire();
			printNumber.accept(i);
			zeroSemaphore.release();
		}
	}
}

public class ZeroEvenOddUsingSemaphore {

	public static void main(String[] args) {

		int n = 5;

		ZeroEvenOddSemaphore sharedObj = new ZeroEvenOddSemaphore(n);

		PrintsZero printsZeroRunnable = new PrintsZero(sharedObj);
		PrintsEven printsEvenRunnable = new PrintsEven(sharedObj);
		PrintsOdd printsOddRunnable = new PrintsOdd(sharedObj);
		
		Thread t1 = new Thread(printsZeroRunnable);
		Thread t2 = new Thread(printsEvenRunnable);
		Thread t3 = new Thread(printsOddRunnable);

		t1.start();
		t2.start();
		t3.start();
	}

	private static class PrintsZero implements Runnable {

		ZeroEvenOddSemaphore zeroEvenOdd;

		public PrintsZero(ZeroEvenOddSemaphore zeroEvenOdd) {
			this.zeroEvenOdd = zeroEvenOdd;
		}

		@Override
		public void run() {

			try {
				zeroEvenOdd.zero(x -> System.out.print(0));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private static class PrintsEven implements Runnable {

		ZeroEvenOddSemaphore zeroEvenOdd;

		public PrintsEven(ZeroEvenOddSemaphore zeroEvenOdd) {
			this.zeroEvenOdd = zeroEvenOdd;
		}

		@Override
		public void run() {

			try {
				zeroEvenOdd.even(x -> System.out.print(x));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	private static class PrintsOdd implements Runnable {

		ZeroEvenOddSemaphore zeroEvenOdd;

		public PrintsOdd(ZeroEvenOddSemaphore zeroEvenOdd) {
			this.zeroEvenOdd = zeroEvenOdd;
		}

		@Override
		public void run() {

			try {
				zeroEvenOdd.odd(x -> System.out.print(x));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
