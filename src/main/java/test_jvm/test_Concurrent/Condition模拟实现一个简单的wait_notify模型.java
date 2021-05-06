package test_jvm.test_Concurrent;

public class Condition模拟实现一个简单的wait_notify模型 {
    public static void main(String[] args) throws InterruptedException {
        Integer a = 2;
        Thread t1 = new Thread(new ThreadCondition(a));
        Thread t2 = new Thread(new ThreadCondition(a));
        t1.start();
        t2.start();
    }

    public static class ThreadCondition implements Runnable {
        Integer a = 0;

        public ThreadCondition(Integer a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                int i = 0;
                while (i < 3) {
                    synchronized (a) {
                        a.notify();
                        System.out.println(Thread.currentThread().getName() + " notify==>wait 循环index：" + i);
                        a.wait();
                        System.out.println(Thread.currentThread().getName() + " wait被唤醒 循环index：" + i);
                    }
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
