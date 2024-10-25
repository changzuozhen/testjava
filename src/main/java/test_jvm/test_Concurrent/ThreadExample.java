package test_jvm.test_Concurrent;

/**
 * 两个线程A和B，共享变量int sum =1
 * 要求：A和B轮番输出线程名+sum，每次输出后sum++
 * 当sum为奇数时，A输出、当sum为偶数时，B输出
 * 当sum>100后，程序停止
 */
public class ThreadExample {
    private static int sum = 1;

    public static void main(String[] args) {
        Object lock = new Object();

        Thread threadA = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (sum > 100) break;
                    if (sum % 2 != 0) {
                        System.out.println("A " + sum);
                        sum++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (sum > 100) break;
                    if (sum % 2 == 0) {
                        System.out.println("B " + sum);
                        sum++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}