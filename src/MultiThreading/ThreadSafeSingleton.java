package MultiThreading;

public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance = null;


    private ThreadSafeSingleton() {
    }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                // first -> true, second -> false
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}

class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
        System.out.println(name + " is executed " + Thread.currentThread().getName() + "\t" + instance.hashCode());
    }
}

class Main {
    public static void main(String[] args) {

        Task firstTask = new Task("Task 1");
        Thread t1 = new Thread(firstTask);
        Task secondTask = new Task("Task 2");
        Thread t2 = new Thread(secondTask);
//        ******************************** //
        Thread t3 = new Thread(firstTask);

        t1.start();
        t2.start();
        t3.start();
    }
}