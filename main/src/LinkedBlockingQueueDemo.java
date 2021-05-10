package src;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);

        queue.put("a");
        queue.put("a");
        queue.put("a");
        //why c == capacity will signal put thread, as
        // c = count.getAndDecrement will return the previous value, its capacity.
        // once the taking one element from queue, it should notify put thread can offer element now.
        queue.take();
    }
}
