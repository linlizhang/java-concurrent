package src;

public class VolatileDemo {

    public static volatile VolatileData data = new VolatileData(1, 1);

    static class DataReader implements Runnable {
        private boolean isStop = false;

        @Override
        public void run() {
            while(!isStop) {
                if (data.getValue() != data.getKey()) {
                    System.out.print("key & value are different: key:" + data.getKey() + ",value:" + data.getValue() + "; ");
                }

            }
        }

        public void stop() {
            isStop = true;
        }
    }

    static class DataWriter implements Runnable {

        @Override
        public void run() {
            for (int i = 2; i <= 1000; i++) {
                data.setKey(i);
                data.setValue(i);
//                data = new VolatileData(i, i);
            }
        }
    }

    public static void main(String[] args) {
        Thread reader = new Thread(new DataReader());
        Thread writer = new Thread(new DataWriter());
        Thread t1 = new Thread(new DataReader());
        writer.start();
        t1.start();
        reader.start();

    }
}

class VolatileData{
    private int value;

    private int key;

    public VolatileData(int name, int i) {
        this.key = name;
        this.value = i;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
