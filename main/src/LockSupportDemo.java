package src;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static Object lock = new Object();

    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");


    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        public void run() {
            synchronized (lock) {
                System.out.println("in " + getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        System.out.println("t1 unpark");
        LockSupport.unpark(t2);
        System.out.println("t2 unpark");
        t1.join();
        t2.join();
    }
}
