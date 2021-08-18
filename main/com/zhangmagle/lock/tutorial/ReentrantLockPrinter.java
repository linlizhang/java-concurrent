package com.zhangmagle.lock.tutorial;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPrinter {

	private static Lock lock = new ReentrantLock();

	private void print(Condition currentThread, Condition nextThread) {
		for (int i = 0; i <5 ;i++) {
			lock.lock();

			System.out.print(Thread.currentThread().getName());
			try {
				nextThread.signal();
				currentThread.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
				
	}

	public static void main(String[] args) {
		ReentrantLockPrinter printer = new ReentrantLockPrinter();
		Condition c1 = lock.newCondition();
		Condition c2 = lock.newCondition();
		Condition c3 = lock.newCondition();

		new Thread(() -> {
			printer.print(c1, c2);
		}, "A").start();

		new Thread(() -> {
			printer.print(c2, c3);
		}, "B").start();

		new Thread(() -> {
			printer.print(c3, c1);
		}, "C").start();
		
	}

}
