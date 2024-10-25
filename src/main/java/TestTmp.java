import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestTmp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Test Exception in Thread Runnable");
                throw new RuntimeException("Test Exception in Thread");
            }
        });

        try {
            Thread.sleep(1000);
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("Exception caught: " + e.getCause());
            } finally {
                executor.shutdown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
