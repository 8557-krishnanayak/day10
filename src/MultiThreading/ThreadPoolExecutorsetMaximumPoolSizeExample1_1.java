package MultiThreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.lang.Thread;

public class ThreadPoolExecutorsetMaximumPoolSizeExample1_1 {

    public static void main(final String[] arguments) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        executor.setMaximumPoolSize(5);
        executor.setCorePoolSize(5);
        System.out.println("MAXIMUM POOL SIZE BEFORE SUBMIT: " + executor.getMaximumPoolSize());

        for (int i = 1; i <= 10; i++) {
            executor.submit(new Task());
        }
//        executor.submit(new Task());
        executor.shutdown();
        System.out.println(" POOL SIZE AFTER SUBMIT:" + executor.getLargestPoolSize());
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Long duration = (long) (Math.random() * 5);
                System.out.println("Running Thread Name: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("Completed Thread Name: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
            }
        }
    }
}