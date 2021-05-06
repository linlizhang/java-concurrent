package src;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static class Soldier implements Runnable {
        private String soldier;

        private final CyclicBarrier cyclicBarrier;

        Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {
            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            } catch (BrokenBarrierException ex) {
                ex.printStackTrace();
            }
        }

        void doWork() throws InterruptedException {
            Thread.sleep(Math.abs(new Random().nextInt()%100000));
            System.out.println(soldier + ": completes task");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int N) {
            this.flag = flag;
            this.N = N;
        }

        public void run() {
            if (flag) {
                System.out.println("General:[" + N + " soldiers, complete task]");
            } else {
                System.out.println("General:[" + N + " soldiers, collection]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoliders = new Thread[N];

        boolean flag = false;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));

        System.out.println("team collections");

        for (int i =0; i < N; i++) {
            System.out.println("Soldier " + i + ": report!");
            allSoliders[i] = new Thread(new Soldier("Soldier" + i, cyclicBarrier));
            //below interruption will lead one thread raising interruptedException,
            // and other 9 threads raising BrokenBarrierException
//            if (i == 5) {
//                allSoliders[0].interrupt();
//            }
            allSoliders[i].start();
        }
    }
}
