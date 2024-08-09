package Threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {
            Task task = new Task("hello_" + i);
            System.out.println("Submitting " + task);
            executorService.execute(task); // wait
        }
        System.out.println("Loop over");
        executorService.shutdown(); //Thread close. run
        System.out.println("Shutdown");
    }

}


class Task implements Runnable {
    String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " is being executed by " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); //some task
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(name + " has completed execution");
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}