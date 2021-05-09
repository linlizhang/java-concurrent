package src;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalGCDemo {

	static volatile ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected void finalize() {
			System.out.println(this.toString() + "is gc");
		}
	};
	
	static volatile CountDownLatch cd = new CountDownLatch(10000);
	
	public static class ParseDate implements Runnable {
		int i = 0;
		
		public ParseDate(int i) {
			this.i = i;
		}
		
		public void run() {
			try {
				if (t1.get() == null) {
					t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
						
						@Override
						protected void finalize() {
							System.out.println("gc is happing in " + this.toString());
						}
					});
					
//					System.out.println(Thread.currentThread().getId() + ": create SimpleDateFormat");
				}
				t1.get().parse("2021-05-9 15:45:" + i%60);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				cd.countDown();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(5);
		
		for (int i = 0; i < 10000; i++) {
			es.execute(new ParseDate(i));
		}
		
		cd.await();
		
		System.out.println("mission completed");
		
		t1 = null;
		System.gc();
		
		System.out.println("first gc completed");
		
		t1 = new ThreadLocal<SimpleDateFormat>();
		cd = new CountDownLatch(100000);
		for (int i = 0; i < 100000; i++) {
			es.execute(new ParseDate(i));
		}
		
		cd.await();
		System.out.println("Second mission completed");
		Thread.sleep(1000);
		t1 = null;
		System.gc();
		System.out.println("Second gc completed");
		
//		es.shutdown();
	}
}
