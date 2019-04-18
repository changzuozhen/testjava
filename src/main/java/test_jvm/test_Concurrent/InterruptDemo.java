package test_jvm.test_Concurrent;

import utils.LogUtils;

/**
 * sleepThread isInterrupted: false
 * busyThread isInterrupted: true
 * java.lang.InterruptedException: sleep interrupted
 * at java.lang.Thread.sleep(Native Method)
 * at test_jvm.test_Concurrent.InterruptDemo$1.run(InterruptDemo.java:10)
 */
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        //sleepThread睡眠1000ms
        final Thread sleepThread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        //busyThread一直执行死循环
        Thread busyThread = new Thread() {
            @Override
            public void run() {
                while (true) ;
            }
        };
        sleepThread.start();
        busyThread.start();
        sleepThread.interrupt();
        busyThread.interrupt();
        while (sleepThread.isInterrupted()) ;
        //当抛出InterruptedException时候，会清除中断标志位，也就是说在调用isInterrupted会返回false。
        LogUtils.d("sleepThread isInterrupted: " + sleepThread.isInterrupted());
        LogUtils.d("busyThread isInterrupted: " + busyThread.isInterrupted());


    }
}
