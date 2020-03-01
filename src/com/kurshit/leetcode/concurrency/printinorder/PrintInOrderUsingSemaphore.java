package com.kurshit.leetcode.concurrency.printinorder;

import java.util.concurrent.Semaphore;


/*
 * Problem : 1114. Print In Order
 * 
 * Source : https://leetcode.com/problems/print-in-order/
 * 
 * Statement: 
 * 	Suppose we have a class - 
 * 
 *  public class Foo {
 * 		public void first() { 
 * 			print("first"); 
 * 		}
 *  	
 *  	public void second() { 
 *  		print("second"); 
 *  	}
 *  	
 *  	public void third() { 
 *  		print("third"); 
 *  	}
 * 
 *  }
 *  
 *  The same instance of Foo will be passed to three different threads. Thread A will call first(),
 *  thread B will call second(), and thread C will call third(). 
 *  
 *  Design a mechanism and modify the program to ensure that second() is executed after first(), 
 *  
 *  and third() is executed after second().
 *  
 *  Input: [1,2,3]
 *	Output: "firstsecondthird"
 *	Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(), 
 *	thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.
 *  
 *  Input: [1,3,2]
 *	Output: "firstsecondthird"
 *	Explanation: The input [1,3,2] means thread A calls first(), 
 *	thread B calls third(), and thread C calls second(). "firstsecondthird" is the correct output.
 *
 *  NOTE : The initial code that was given at the below
 *	
 *	
 */

/*
 * SOLUTION 2 : Using Semaphore
 */

class FooSemaphore {

	Semaphore semaphore1 = new Semaphore(0);
	Semaphore semaphore2 = new Semaphore(0);

	public FooSemaphore() {

	}

	public void first(Runnable printFirst) throws InterruptedException {
		printFirst.run();
		semaphore1.release();
		
	}

	public void second(Runnable printSecond) throws InterruptedException {
		semaphore1.acquire();
		printSecond.run();
		semaphore2.release();
		
	}

	public void third(Runnable printThird) throws InterruptedException {
		semaphore2.acquire();
		printThird.run();
		
	}

	/*
	 * FOllowing is the driver code written to test it. I need to create three
	 * threads and make run() to print one, two and three - these three different
	 * tasks and use only one instance of PrintInOrderUsingVolatile (initial class
	 * name FOO) to call these threads with
	 * 
	 */
}

public class PrintInOrderUsingSemaphore {

	public static void main(String[] args) {

		FooSemaphore foo = new FooSemaphore();
		PrintsFirst printsFirsts = new PrintsFirst();
		CallsFirst callsFirsts = new CallsFirst(foo, printsFirsts);

		PrintsSecond printsSeconds = new PrintsSecond();
		CallsSecond callsSeconds = new CallsSecond(foo, printsSeconds);

		PrintsThird printsThirds = new PrintsThird();
		CallsThird callsThirds = new CallsThird(foo, printsThirds);

		Thread t3 = new Thread(callsThirds);
		Thread t2 = new Thread(callsSeconds);
		Thread t1 = new Thread(callsFirsts);

		t1.start();
		t2.start();
		t3.start();
	}

	private static class PrintsFirst implements Runnable {

		public PrintsFirst() {

		}

		public void run() {
			System.out.println("first");
		}

	}

	private static class CallsFirst implements Runnable {
		private FooSemaphore foo;
		private PrintsFirst first;

		public CallsFirst(FooSemaphore foo, PrintsFirst first) {
			this.foo = foo;
			this.first = first;
		}

		@Override
		public void run() {
			try {
				foo.first(first);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private static class PrintsSecond implements Runnable {

		public void run() {
			System.out.println("second");
		}

	}

	private static class CallsSecond implements Runnable {
		private FooSemaphore foo;
		private PrintsSecond seconds;

		public CallsSecond(FooSemaphore foo, PrintsSecond seconds) {
			this.foo = foo;
			this.seconds = seconds;
		}

		@Override
		public void run() {
			try {
				foo.second(seconds);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private static class PrintsThird implements Runnable {

		public void run() {
			System.out.println("third");
		}

	}

	private static class CallsThird implements Runnable {
		private FooSemaphore foo;
		private PrintsThird thirds;

		public CallsThird(FooSemaphore foo, PrintsThird thirds) {
			this.foo = foo;
			this.thirds = thirds;
		}

		@Override
		public void run() {
			try {
				foo.third(thirds);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

/*
 * Below is how initial code was given without main method
 * 
 * public void first(Runnable printFirst) throws InterruptedException {
 * 
 * // printFirst.run() outputs "first". Do not change or remove this line.
 * printFirst.run(); }
 * 
 * public void second(Runnable printSecond) throws InterruptedException {
 * 
 * // printSecond.run() outputs "second". Do not change or remove this line.
 * printSecond.run(); }
 * 
 * public void third(Runnable printThird) throws InterruptedException {
 * 
 * // printThird.run() outputs "third". Do not change or remove this line.
 * printThird.run(); }
 * 
 * public static void main(String[] args) { // TODO Auto-generated method stub
 * 
 * }
 * 
 */
