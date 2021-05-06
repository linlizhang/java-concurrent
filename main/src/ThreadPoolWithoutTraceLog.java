package src;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolWithoutTraceLog {

    public static void main(String[] args) {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            //only four results, the first one is gone.
            pools.submit(new DivTask(100, i));
        }
    }
}
