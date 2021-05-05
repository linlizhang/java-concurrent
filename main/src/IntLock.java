package src;

import java.util.concurrent.locks.ReentrantLock;

public class IntLock implements Runnable {

	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();

	int lock;

	public IntLock(int lock) {
		this.lock = lock;
	}

	public void run() {
		try {
			if (lock == 1) {

				lock1.lockInterruptibly();
				System.out.println(Thread.currentThread().getId() + ": acquired lock1");

//				try {
				Thread.sleep(500);
//				} catch (InterruptedException e) {}
				lock2.lockInterruptibly();
			} else {

				lock2.lockInterruptibly();
				System.out.println(Thread.currentThread().getId() + ": acquired lock2");

//				try {
				Thread.sleep(500);
//				} catch (InterruptedException e) {}
				lock1.lockInterruptibly();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (lock1.isHeldByCurrentThread()) {
				System.out.println(Thread.currentThread().getId() + ": release lock1");

				lock1.unlock();
			}
			if (lock2.isHeldByCurrentThread()) {
				System.out.println(Thread.currentThread().getId() + ": release lock2");

				lock2.unlock();
			}
			
			System.out.println(Thread.currentThread().getId() + ": thread exits");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntLock r1 = new IntLock(1);
		IntLock r2 = new IntLock(2);
		new Thread(r1).start();
		Thread t2 = new Thread(r2);
		t2.start();
		
		Thread.sleep(1000);
		System.out.println("Tryiny to interrupt thread t2");
		t2.interrupt();
	}
}
