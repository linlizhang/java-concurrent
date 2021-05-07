package src;

public class RealData implements Data {

    protected final String result;

    public RealData(String data) {
        StringBuffer sb  = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(data);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        result = sb.toString();
    }

    public String getResult() {
        return result;
    }
}
