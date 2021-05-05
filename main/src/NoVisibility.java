package src;

public class NoVisibility {
	
	// volatile 只能保证一个线程修改了数据后，其他线程能够看到这个改动。
	private static volatile boolean ready;
	private static int number;
	
	
	private static class ReaderThread extends Thread {
		public void run() {
			System.out.println("Reader thread is started");

			//This thread does not stop if ready is not volatile
			while(!ready);
			
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread reader = new ReaderThread();
		reader.start();
		Thread.sleep(1000);
		
		number = 42;
		ready = true;
		Thread.sleep(10000);
		
		System.out.println("Main thread is exiting");
	}

}
