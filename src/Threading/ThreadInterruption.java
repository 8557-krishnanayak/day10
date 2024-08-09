package Threading;

public class ThreadInterruption {
    public static void main(String[] args) {
        Thread taskThread = new Thread(new Demo());
        taskThread.start();

        try {
            Thread.sleep(2000); // Let the task run for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hello");
        // Interrupt the task thread
        taskThread.interrupt();
    }
}

class Demo implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Task is running...");
            try {
                Thread.sleep(500); // Simulate some work
            } catch (InterruptedException e) {
                System.out.println("Task was interrupted!");
                Thread.currentThread().interrupt(); // Preserve the interrupt status
            }
        }
        System.out.println("Task has been cancelled.");
    }
}
