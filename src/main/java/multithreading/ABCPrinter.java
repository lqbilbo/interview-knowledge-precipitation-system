package multithreading;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Semaphore实现
 */
/* ****
public class ABCPrinter {
    private final int max;

    private final Semaphore semaphoreA = new Semaphore(1);
    private final Semaphore semaphoreB = new Semaphore(0);
    private final Semaphore semaphoreC = new Semaphore(0);

    public ABCPrinter(int max) {
        this.max = max;
    }

    public void printA() {
        print("A", semaphoreA, semaphoreB);
    }

    public void printB() {
        print("B", semaphoreB, semaphoreC);
    }

    public void printC() {
        print("C", semaphoreC, semaphoreA);
    }

    private void print(String alphabet, Semaphore currentSemaphore, Semaphore nextSemaphore) {
        for (int i = 0; i < max; i++) {
            try {
                currentSemaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " : " + alphabet);
                nextSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
**** */

/**
 * ReentrantLock + Condition实现
 */
public class ABCPrinter {
    private final int max;
    private int turn = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition conditionA = lock.newCondition();
    private final Condition conditionB = lock.newCondition();
    private final Condition conditionC = lock.newCondition();

    public ABCPrinter(int max) {
        this.max = max;
    }

    public void printA() {
        print("A", conditionA, conditionB);
    }

    public void printB() {
        print("B", conditionB, conditionC);
    }

    public void printC() {
        print("C", conditionC, conditionA);
    }

    private void print(String name, Condition currentCondition, Condition nextCondition) {
        for (int i = 0; i < max; i++) {
            lock.lock();
            try {
                while (!((turn == 0 && name.charAt(0) == 'A')
                        || (turn == 1 && name.charAt(0) == 'B')
                        || (turn == 2 && name.charAt(0) == 'C'))) {
                    currentCondition.await();
                }
                System.out.println(Thread.currentThread().getName() + " : " + name);
                turn = (turn + 1) % 3;
                nextCondition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ABCPrinter printer = new ABCPrinter(5);
        Thread t1 = new Thread(printer::printA, "Thread A");
        Thread t2 = new Thread(printer::printB, "Thread B");
        Thread t3 = new Thread(printer::printC, "Thread C");

        t1.start();
        t2.start();
        t3.start();
    }
}
