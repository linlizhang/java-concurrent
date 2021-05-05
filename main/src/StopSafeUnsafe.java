package src;

public class StopSafeUnsafe {
	
	public static User user = new  User();
	
	public static class User {
		private int id;
		
		private String name;
		
		public User() {
			id = 0; 
			name = "0";
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public int getId() {
			return id;
		}
		
		public String getName( ) {
			return name;
		}
		
		public String toString() {
			return "User [id=" + id + ", name = " + name + "]";
		}
	}
	
	public static class ChangeObject extends Thread {
		public void run() {
			while(true) {
				synchronized(user) {
					int v = (int)(System.currentTimeMillis()/1000);
					user.setId(v);
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					user.setName(String.valueOf(v));
				}
				Thread.yield();
			}
		}
	}
	
	public static class ReadObject extends Thread {
		
		public void run() {
			while(true) {
				synchronized(user) {
					if (user.getId() != Integer.parseInt(user.getName())) {
						System.out.println(user);
					}
				}
				Thread.yield();
			}
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		new ReadObject().start();
		
		while(true) {
			Thread writer = new ChangeObject();
			writer.start();
			
			Thread.sleep(150);
			/*
			 * Thread.stop() will terminate it when thread stops and release all locks it holds. 
			 * During writing an object, its thread stops forcedly, this object will be broken 
			 */
			writer.stop();
		}
	}

}
