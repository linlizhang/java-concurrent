package src;

public final class PCData {

    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String d) {
        this.intData = Integer.valueOf(d);
    }

    public int getIntData() {
        return this.intData;
    }

    public String toString() {
        return "data: " + intData;
    }
}
