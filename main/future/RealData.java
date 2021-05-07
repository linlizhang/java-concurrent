package future;

import java.util.concurrent.Callable;

public class RealData implements Callable<String> {

    private String data;

    public RealData(String data) {
        this.data = data;
    }

    public String call() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(data);
        }
        try {
            //time consuming action
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        return sb.toString();
    }
}
