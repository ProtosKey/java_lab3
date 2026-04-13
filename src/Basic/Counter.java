package Basic;

public class Counter {
    private static int id = 0;
    public static int next() {
        return id++;
    }
}
