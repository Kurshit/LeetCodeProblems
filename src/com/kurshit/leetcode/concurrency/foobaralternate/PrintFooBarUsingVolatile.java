package com.kurshit.leetcode.concurrency.foobaralternate;

class FooBarVolatile {

	private int n;

	public FooBarVolatile(int n) {
		this.n = n;
	}

	volatile boolean isPrintingFoo = true;

	public void foo(Runnable printFoo) throws InterruptedException {

		for (int i = 0; i < n; i++) {

			while (!isPrintingFoo) {

			}
			// printFoo.run() outputs "foo". Do not change or remove this line.
			printFoo.run();

			isPrintingFoo = false;

		}

	}

	public void bar(Runnable printBar) throws InterruptedException {

		for (int i = 0; i < n; i++) {

			while (isPrintingFoo) {

			}
			// printBar.run() outputs "bar". Do not change or remove this line.
			printBar.run();
			isPrintingFoo = true;

		}
	}

}

public class PrintFooBarUsingVolatile {

	public static void main(String[] args) {
		int n = 50;
		FooBarVolatile foobar = new FooBarVolatile(n);
		PrintsFoo printsFoo = new PrintsFoo();
		CallsFoo callsFoo = new CallsFoo(foobar, printsFoo);

		PrintsBar printsBar = new PrintsBar();
		CallsBar callsBar = new CallsBar(foobar, printsBar);

		Thread t1 = new Thread(callsFoo);
		Thread t2 = new Thread(callsBar);

		t1.start();
		t2.start();

		// TODO Auto-generated method stub

	}

	private static class PrintsFoo implements Runnable {

		public PrintsFoo() {

		}

		public void run() {
			System.out.print("foo");
		}

	}

	private static class CallsFoo implements Runnable {
		private FooBarVolatile foobar;
		private PrintsFoo firstFoo;

		public CallsFoo(FooBarVolatile foobar, PrintsFoo firstFoo) {
			this.foobar = foobar;
			this.firstFoo = firstFoo;
		}

		@Override
		public void run() {
			try {
				foobar.foo(firstFoo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static class PrintsBar implements Runnable {

		public PrintsBar() {

		}

		public void run() {
			System.out.print("bar");
		}

	}

	private static class CallsBar implements Runnable {
		private FooBarVolatile foobar;
		private PrintsBar secondBar;

		public CallsBar(FooBarVolatile foobar, PrintsBar secondBar) {
			this.foobar = foobar;
			this.secondBar = secondBar;
		}

		@Override
		public void run() {
			try {
				foobar.bar(secondBar);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
