package GarbageCollection;

public class Main {

    public static void main(String[] args) {
        GCExample g = new GCExample("1");
        GCExample h = new GCExample("2");
        GCExample i = new GCExample("3");


        g = null;
        h = i;


        System.gc();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
