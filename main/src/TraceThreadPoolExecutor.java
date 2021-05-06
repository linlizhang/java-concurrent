package src;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
            long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> queue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, queue);
    }

    public void execute(Runnable task) {
        super.execute(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Exception clientTrace() {
        return new Exception("Client stack trace");
    }

    private Runnable wrap(final Runnable task, final Exception clientStack, final String name) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        };
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pools = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            //only four results, the first one is gone.
            pools.submit(new DivTask(100, i));
        }
    }
}
