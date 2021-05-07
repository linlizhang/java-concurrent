package src;

public class FutureClient {

    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread() {
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();

        return futureData;
    }

    public static void main(String[] args) {
        FutureClient client = new FutureClient();

        Data data = client.request("name");

        System.out.println("Request for data is done");

        //do other jobs

        //main thread is waiting for data preparing.
        //in this case, the main thread cannot do other job
        System.out.println("data = " + data.getResult());
        System.out.println("exiting main thread");
    }
}
