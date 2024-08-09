package MultiThreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {
    private static final Lock l1 = new ReentrantLock();
    private static final Lock l2 = new ReentrantLock();

    public static void main(String[] args) {
        // Thread1
        new Thread(() -> {
            while (true) {
                try {
                    if (!l1.tryLock(1000, TimeUnit.SECONDS)) {
                        continue;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    if (!l2.tryLock(1000, TimeUnit.SECONDS)) {
                        l1.unlock();
                        continue;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // do some work
                System.out.println("L1");

                l2.unlock();
                l1.unlock();
            }
        }).start();

        // Thread2
        new Thread(() -> {
            while (true) {
                try {
                    if (!l2.tryLock(1000, TimeUnit.SECONDS)) {
                        continue;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    if (!l1.tryLock(1000,TimeUnit.SECONDS)) {
                        l2.unlock();
                        continue;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // do some work

                System.out.println("L2");


                l1.unlock();
                l2.unlock();
            }
        }).start();
    }
}
