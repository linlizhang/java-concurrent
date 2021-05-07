package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService es = Executors.newFixedThreadPool(1);

        es.execute(future);
        System.out.println("request complete");

        //to other jobs

        System.out.println("task result: " + future.get());

        es.shutdown();

    }
}
