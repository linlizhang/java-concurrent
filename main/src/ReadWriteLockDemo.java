package src;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//todo improve 
public class ReadWriteLockDemo {

	private static Lock lock = new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();

	private int value;

	public Object handleRead(Lock lock) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);
			System.out.println("read value: " + value);
			return value;
		} finally {
			lock.unlock();
		}
	}

	public void handleWrite(Lock lock, int index) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);
			System.out.println("write value: " + value);
			value = index;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final ReadWriteLockDemo demo = new ReadWriteLockDemo();
		Runnable readRunnable = new Runnable() {
			public void run() {
				try {
//					demo.handleRead(lock);
					demo.handleRead(readLock);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Runnable writeRunnable = new Runnable() {
			public void run() {
				try {
//					demo.handleWrite(lock, new Random().nextInt());
					demo.handleWrite(writeLock, new Random().nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};
		long start = System.currentTimeMillis();
		for (int i = 0; i < 18; i++) {
			new Thread(readRunnable).start();
		}
		
		for (int i = 0; i < 18; i++) {
			new Thread(writeRunnable).start();
		}
		System.out.println("time elapsed: " + (System.currentTimeMillis() - start) + "ms");
	}
}
