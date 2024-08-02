package multithreading;

import java.util.concurrent.Semaphore;

public class PrintOddEven {

    private static Semaphore semaphoreEven = new Semaphore(0);
    private static Semaphore semaphoreOdd = new Semaphore(1);

    static class EvenThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i+=2) {
                try {
                    semaphoreOdd.acquire();
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphoreEven.release();
                }
            }
        }
    }

    static class OddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i < 100; i+=2) {
                try {
                    semaphoreEven.acquire();
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphoreOdd.release();
                }
            }
        }
    }
    public static void main(String[] args) {
//        new Thread(new EvenThread()).start();
//        new Thread(new OddThread()).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i+=2) {
                try {
                    semaphoreOdd.acquire();
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphoreEven.release();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i < 100; i+=2) {
                try {
                    semaphoreEven.acquire();
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphoreOdd.release();
                }
            }
        }).start();
    }
}
