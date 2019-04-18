package test_jvm.test_Concurrent;

import utils.LogUtils;

import java.util.concurrent.*;

public class CreateThreadDemo {

    public static void main(String[] args) {
        //1.继承Thread
        Thread thread = new Thread() {
            @Override
            public void run() {
                LogUtils.d("继承Thread");
                super.run();
            }
        };
        thread.start();
        //2.实现runable接口
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtils.d("实现runable接口");
            }
        });
        thread1.start();
        //3.实现callable接口
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable() {
            @Override
            public String call() throws Exception {
                LogUtils.d("Callable 1");
                Thread.sleep(3000);
                LogUtils.d("Callable 2");
                return "通过实现Callable接口";
            }
        });
        try {
            LogUtils.d("Callable 0");
            String result = future.get();
            LogUtils.d("Callable 4");
            LogUtils.d(result);
            LogUtils.d("Callable 5");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

}
