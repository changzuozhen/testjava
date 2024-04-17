package test_jvm.test_Concurrent;

import utils.LogUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这段代码是一个使用Lock和Condition实现线程等待和唤醒的示例。
 * <p>
 * 代码中定义了一个AwaitSignalExample类，其中包含了一个Lock对象和一个Condition对象。Lock对象用于实现线程的互斥访问，Condition对象用于实现线程的等待和唤醒。
 * <p>
 * 在before()方法中，首先通过lock.lock()获取锁，然后输出"before"的日志信息，最后通过condition.signalAll()方法唤醒所有等待在该Condition上的线程。最后使用lock.unlock()释放锁。
 * <p>
 * 在after()方法中，首先通过lock.lock()获取锁，然后通过condition.await()方法使当前线程等待在该Condition上，直到被其他线程通过condition.signalAll()方法唤醒。在被唤醒后，输出"after"的日志信息。如果在等待过程中被中断，则会抛出InterruptedException异常。最后使用lock.unlock()释放锁。
 * <p>
 * 在main()方法中，创建了一个线程池executorService，并创建了一个AwaitSignalExample对象example。然后通过executorService.execute()方法分别执行example.after()和example.before()方法，即在两个线程中分别调用after()和before()方法。
 * <p>
 * 整个代码的执行流程如下：
 * <p>
 * 创建线程池executorService和AwaitSignalExample对象example。
 * 在executorService中执行example.after()方法的线程，该线程会获取锁并等待在condition上。
 * 在executorService中执行example.before()方法的线程，该线程会获取锁并唤醒在condition上等待的线程。
 * 被唤醒的线程继续执行，输出"after"的日志信息。
 * 这段代码的作用是通过Lock和Condition实现线程的等待和唤醒。在before()方法中，通过condition.signalAll()方法唤醒在condition上等待的线程；在after()方法中，通过condition.await()方法使当前线程等待在condition上，直到被其他线程唤醒。通过这种方式，可以实现线程之间的协作和同步。
 */
public class AwaitSignalExample {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void before(int millis) {
        lock.lock();
        try {
            LogUtils.d("before() before sleep " + millis);
            Thread.sleep(millis);
            LogUtils.d("before() after sleep " + millis);
            condition.signalAll();
            LogUtils.d("before() after condition.signalAll(); " + millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void after(int millis) {
        lock.lock();
        try {
            LogUtils.d("after() before condition.await();" + millis);
            condition.await();
            LogUtils.d("after() after condition.await();" + millis);
            Thread.sleep(millis);
            LogUtils.d("after() after sleep " + millis);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AwaitSignalExample example = new AwaitSignalExample();
        executorService.execute(() -> example.after(100));
        executorService.execute(() -> example.after(200));
        executorService.execute(() -> example.after(200));
        executorService.execute(() -> example.before(1000));
        executorService.execute(() -> example.before(2000));
        executorService.execute(() -> example.before(3000));
    }
}