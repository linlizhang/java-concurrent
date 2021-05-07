package src;

import java.text.MessageFormat;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    public void run() {
        System.out.println("Start consumer id=" + Thread.currentThread().getId());

        try {
            while(isRunning) {
                PCData data = queue.take();
                if (null != data) {
                    int rs = data.getIntData() * data.getIntData();
                    System.out.println(MessageFormat.format("{0}*{1} = {2}",
                            data.getIntData(), data.getIntData(), rs));

                    Thread.sleep(SLEEPTIME);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
