package com.kurshit.leetcode.concurrency.printinorder;

/*
 * Problem : 1114. Print In Order
 * Source : https://leetcode.com/problems/print-in-order/
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
 * Solution 1 : Using Volatile
 */

class FooVolatile {

	public volatile int flag;

	public FooVolatile() {
		flag = 1;
	}

	public void first(Runnable printFirst) throws InterruptedException {

		while (true) {

			if (flag == 1) {
				// printFirst.run() outputs "first". Do not change or remove this line.

				// Thread.sleep(5000); this was to test the code
				printFirst.run();
				flag = 2;
				break;
			}
		}

	}

	public void second(Runnable printSecond) throws InterruptedException {

		while (true) {
			if (flag == 2) {
				// printSecond.run() outputs "second". Do not change or remove this line.
				// Thread.sleep(5000); to test
				printSecond.run();
				flag = 3;
				break;
			}
		}

	}

	public void third(Runnable printThird) throws InterruptedException {

		while (true) {

			if (flag == 3) {
				// printThird.run() outputs "third". Do not change or remove this line.
				// Thread.sleep(5000); to test
				printThird.run();
				flag = 1;
				break;
			}
		}

	}

	/*
	 * FOllowing is the driver code written to test it. I need to create three
	 * threads and make run() to print one, two and three - these three different
	 * tasks and use only one instance of PrintInOrderUsingVolatile (initial class
	 * name FOO) to call these threads with
	 * 
	 */
}

public class PrintInOrderUsingVolatile {

	public static void main(String[] args) {

		FooVolatile foo = new FooVolatile();
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
		private FooVolatile foo;
		private PrintsFirst first;

		public CallsFirst(FooVolatile foo, PrintsFirst first) {
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
		private FooVolatile foo;
		private PrintsSecond seconds;

		public CallsSecond(FooVolatile foo, PrintsSecond seconds) {
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
		private FooVolatile foo;
		private PrintsThird thirds;

		public CallsThird(FooVolatile foo, PrintsThird thirds) {
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
