package GarbageCollection;

public class GCExample {
    String name;

    public GCExample(String n) {
        name = n;
        System.out.println("GCE CREATED");
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("GCE EXAMPLE is deleted:\t" + name);
    }
}
