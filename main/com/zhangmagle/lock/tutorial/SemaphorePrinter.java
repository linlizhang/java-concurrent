package com.zhangmagle.lock.tutorial;

import java.util.concurrent.Semaphore;

public class SemaphorePrinter {

	private void print(Semaphore currentThread, Semaphore nextThread) {
		for (int i = 0; i < 5; i++) {
			try {
				currentThread.acquire();
				System.out.print(Thread.currentThread().getName());
				nextThread.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		SemaphorePrinter printer = new SemaphorePrinter();
		
		Semaphore s1 = new Semaphore(1);
		Semaphore s2 = new Semaphore(0);
		Semaphore s3 = new Semaphore(0);
		
		new Thread(() -> {
			printer.print(s1,  s2);
		}, "A").start();
		
		
		new Thread(() -> {
			printer.print(s2,  s3);
		}, "B").start();
		
		
		new Thread(() -> {
			printer.print(s3,  s1);
		}, "C").start();
	}

}
