package GarbageCollection.LargeData;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<LargeData> dataList = new ArrayList<>();

        // Create instances of LargeData
        for (int i = 0; i < 1000; i++) {
            dataList.add(new LargeData(1000000)); // Each LargeData object has a large array
        }

        printMemoryUsage("Before clearing the list");

        // list clean
        dataList.clear();

        System.gc();

        // Adding a delay to ensure garbage collection happens
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        printMemoryUsage("After clearing the list");
    }

    // Method to print memory usage
    private static void printMemoryUsage(String message) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(message);
        System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) + " bytes");
        System.out.println("Free memory: " + runtime.freeMemory() + " bytes");
        System.out.println("Total memory: " + runtime.totalMemory() + " bytes");
        System.out.println("Max memory: " + runtime.maxMemory() + " bytes");
    }
}
