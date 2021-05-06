package src;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {

    /*
     when there are 10 threads completing their tasks,  countdown will count to zero.
     and the main thread will continue its task.
      */
    static final CountDownLatch end = new CountDownLatch(10);

    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("Check complete");
            end.countDown();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i =0; i < 10; i++) {
            exec.submit(demo);
        }

        System.out.println("Countdown latch in waiting");
        end.await();
        System.out.println("Trying to shutdown thread pool");
        exec.shutdown();
    }
}
