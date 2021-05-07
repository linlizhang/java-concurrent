package sockerServer;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class HeavySocketClient {

    private static ExecutorService es = Executors.newCachedThreadPool();
    private static final int SLEEP_TIME = 1000 *1000 * 1000;

    public static class EchoClient implements Runnable {

        public void run() {
            Socket client = null;
            PrintWriter writer = null;

            BufferedReader reader = null;
            try {
                client = new Socket();
                client.connect(new InetSocketAddress("localhost", 8000));
                writer = new PrintWriter(client.getOutputStream(), true);
                writer.print("H");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("e");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("l");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("l");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("o");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("!");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.println();
                writer.flush();

                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println("from server: " + reader.readLine());
            } catch (IOException exception) {
                exception.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                    if (reader != null) {
                        reader.close();
                    }

                    client.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        EchoClient client = new EchoClient();
        for (int i = 0; i < 10; i++) {
            es.execute(client);
        }
        es.shutdown();
    }
}
