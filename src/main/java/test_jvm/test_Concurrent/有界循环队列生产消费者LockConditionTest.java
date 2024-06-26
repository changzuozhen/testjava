package test_jvm.test_Concurrent;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 原文链接：https://blog.csdn.net/hellozpc/article/details/121722896
 */
public class 有界循环队列生产消费者LockConditionTest {
    private String[] elements = new String[10];//容器底层的数据结构
    private Lock lock = new ReentrantLock();//锁对象
    private Condition notFullCondition = lock.newCondition();//不为满条件
    private Condition notEmptyCondition = lock.newCondition();//不为空条件
    private int elementCount;//数组elements中的元素数量
    private int putIndex;//写指针
    private int takeIndex;//读指针

    /**
     * 放数据
     *
     * @param element
     * @throws InterruptedException
     */
    public void put(String element) throws InterruptedException {
        lock.lock();
        try {
            while (elementCount == elements.length) {
                notFullCondition.await();
            }
            elements[putIndex] = element;
            if (++putIndex == elements.length) {
                putIndex = 0;
            }
            elementCount++;
            System.out.println("after put :" + Arrays.asList(elements));
            notEmptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 取数据
     *
     * @return
     * @throws InterruptedException
     */
    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (elementCount == 0) {
                notEmptyCondition.await();
            }
            String element = elements[takeIndex];
            elements[takeIndex] = null;
            if (++takeIndex == elements.length) {
                takeIndex = 0;
            }
            elementCount--;
            System.out.println("after take:" + Arrays.asList(elements));
            notFullCondition.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //启动10个读线程和10个写线程
        有界循环队列生产消费者LockConditionTest boundedContainer = new 有界循环队列生产消费者LockConditionTest();

        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            try {
                boundedContainer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            try {
                boundedContainer.put("hi~~");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

    }
}

