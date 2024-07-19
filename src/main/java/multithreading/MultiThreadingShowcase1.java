package multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadingShowcase1 {
    private int threadCount;  // 线程数量

    public MultiThreadingShowcase1(int threadCount) {
        this.threadCount = threadCount;
    }

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadCount,
            threadCount+10,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10)
    );

    public void play() {
        int count = this.threadCount;
        for (int i = 0; i < count; i++) {
            int index = i;
            threadPoolExecutor.execute(()-> {
                System.out.println("thread-"+index+"start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
        }
        threadPoolExecutor.shutdown();
    }

    public static void main(String[] args) {
        MultiThreadingShowcase1 mts = new MultiThreadingShowcase1(10);
        mts.play();
    }

}
