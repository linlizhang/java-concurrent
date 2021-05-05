package src;

public class BadLockOnInteger implements Runnable{
	public static Integer i =0;
	static BadLockOnInteger instance = new BadLockOnInteger();
	
	public void run() {
		for (int j = 0; j < 1000; j++) {
			//Integer 属于不变对象，每次就会新建一个Integer
			// 所以应该以instance 为监视器
			synchronized(instance) {
				i++;
			}
		}
	}
	

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
