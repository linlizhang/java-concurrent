package src;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemapDemo implements Runnable {
	
	final Semaphore semp = new Semaphore(5);
	
	public void run() {
		try {
			semp.acquire();
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId() + ":done!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semp.release();
		}
		System.out.println(Thread.currentThread().getId() + " exiting");
	}
	
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(20);
		final SemapDemo demo = new SemapDemo();
		for (int i = 0; i < 20; i++) {
			exec.submit(demo);
		}
		System.out.println( "Main thread exiting");
		exec.shutdown();
	}

}
