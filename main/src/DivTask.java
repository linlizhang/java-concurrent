package src;

public class DivTask implements Runnable {
    int a, b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        double re = a/b;
        System.out.println(re);
    }
}
