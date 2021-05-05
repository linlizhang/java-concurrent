package src;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo implements Runnable {
	
	public static ReentrantLock lock = new ReentrantLock();
	
	public static Condition condition = lock.newCondition();
	
	public void run() {
		try {
			lock.lock();
			condition.await();
			System.out.println("Thread is going on");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ConditionDemo demo = new ConditionDemo();
		Thread t1 = new Thread(demo);
		
		t1.start();
		System.out.println("Main thread is going to sleep 2000");
		Thread.sleep(2000);
		lock.lock();
		System.out.println("Main thread acquired lock");
		condition.signal();
		lock.unlock();
		System.out.println("Main thread released lock");

	}
}
