package test_jvm.test_Concurrent;

import utils.LogUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue
 * java.util.concurrent.BlockingQueue 接口有以下阻塞队列的实现：
 * <p>
 * FIFO 队列 ：LinkedBlockingQueue、ArrayListBlockingQueue（固定长度）
 * 优先级队列 ：PriorityBlockingQueue
 * 提供了阻塞的 take() 和 put() 方法：如果队列为空 take() 将阻塞，直到队列中有内容；如果队列为满 put() 将阻塞，指到队列有空闲位置。
 * <p>
 * 使用 BlockingQueue 实现生产者消费者问题
 */
public class ProducerConsumer {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    private static class Producer extends Thread {
        int mIndex;

        public Producer(int index) {
            super("Producer " + index);
            mIndex = index;
        }

        @Override
        public void run() {
            String product = "product:" + mIndex;
            try {
                queue.put(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtils.log("produce.. " + product + " remain:" + queue.remainingCapacity() + " isEmpty:" + queue.isEmpty(), mIndex);
        }
    }

    private static class Consumer extends Thread {
        int mIndex;

        public Consumer(int index) {
            super("Consumer " + index);
            this.mIndex = index;
        }

        @Override
        public void run() {
            String product = null;
            try {
                product = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtils.log("consume.. " + product + " remain:" + queue.remainingCapacity() + " isEmpty:" + queue.isEmpty(), mIndex);
        }
    }

    public static void main(String[] args) {
        LogUtils.log("main..    " + " remain:" + queue.remainingCapacity() + " isEmpty:" + queue.isEmpty(), 10);
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer(i);
            producer.start();
        }
        for (int i = 0; i < 6; i++) {
            Consumer consumer = new Consumer(-i - 1);
            consumer.start();
        }
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer(i + 3);
            producer.start();
        }
        LogUtils.log("main..    " + " remain:" + queue.remainingCapacity() + " isEmpty:" + queue.isEmpty(), 10);
    }
}
