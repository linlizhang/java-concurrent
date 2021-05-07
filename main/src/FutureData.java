package src;

public class FutureData implements Data {

    protected RealData realData = null;

    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if(isReady) {
            return;
        }

        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    public synchronized String getResult() {
        while(!isReady) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("retrieving real data successfully");
        return realData.result;
    }
}
