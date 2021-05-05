package src;

public class WaitNotifyDemo {
	
	final static Object lock = new Object();
	
	/*
	 * lock.wait()必须包含在对应的synchronized语句中， 无论是wait() or notify() 都需要首先
	 * 获得目标对象的一个监视器。
	 *
	 *wait()方法会释放目标对象的锁， 而Thread.sleep()方法不会释放任何资源
	 */
	public static class T1 extends Thread {
		public void run() {
			synchronized(lock) {
				System.out.println(System.currentTimeMillis() + ": T1 start!");
				try {
					System.out.println(System.currentTimeMillis() + ": T1 wait for lock");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis() + ":T1 end!");
			}
		}
	}
	
	
	public static class T2 extends Thread {
		public void run() {
			synchronized(lock) {
				System.out.println(System.currentTimeMillis() + ":T2 start! notify one thread");
				lock.notify();
				System.out.println(System.currentTimeMillis() + ":T2 end!");
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new T1();
		Thread t2 = new T2();
		
		t1.start();
		t2.start();
	}

}
