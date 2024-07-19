package threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ThreadPoolTest {

    Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
            2,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public void execute() {
        threadPoolExecutor.execute(()-> {
            System.out.println("core thread execute first task");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadPoolExecutor.execute(()-> {
            System.out.println("non-core thread execute second task");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadPoolExecutor.execute(()-> {
            System.out.println("non-core thread execute third task");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadPoolExecutor.execute(()-> {
            System.out.println("main thread execute four task");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadPoolExecutor.execute(()-> {
            System.out.println("core thread execute five task");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        ThreadPoolTest test = new ThreadPoolTest();
        test.execute();
    }
}
