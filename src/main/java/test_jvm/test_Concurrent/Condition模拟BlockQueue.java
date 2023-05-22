package test_jvm.test_Concurrent;

import utils.LogUtils;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://www.jianshu.com/p/a2a6172fbdfb
 */
public class Condition模拟BlockQueue {


    public static void main(String[] args) throws InterruptedException {
        //创建一个队列
        Queue queue = new Queue(10);
        //创建一个生产者，往队列里put数据
        Produce produce = new Produce(queue);
        Thread t = new Thread(produce);
        t.start();
        //Thread.sleep(3000);
        //创建一个消费者，用来取数据
        Consumer consumer = new Consumer(queue);
        Thread t1 = new Thread(consumer);
        t1.start();
    }

    static class Produce implements Runnable {
        Queue q = null;

        public Produce(Queue queue) {
            this.q = queue;
        }

        @Override
        public void run() {
            try {
                long begin = System.currentTimeMillis();
                for (int i = 0; i < 10; i++) {
                    q.put(new String("aa"));
                    q.put(new String("bb"));
                    q.put(new String("cc"));
                }
                System.out.println("耗时：" + (System.currentTimeMillis() - begin));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        Queue q = null;

        public Consumer(Queue queue) {
            this.q = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    Object obj1 = q.take();
                    Object obj2 = q.take();
                    Object obj3 = q.take();
                    System.out.println((String) obj1.toString() + "   " + (String) obj2.toString() + "   " + (String) obj3.toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建一个简单的数组队列
     */
    private static class Queue {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        LinkedList<Object> obj = new LinkedList<Object>();
        private int size = 0;
        private int maxSize = 0;

        public Queue(int i) {
            maxSize = i;
            obj = new LinkedList<Object>();
        }

        //往队列里加数据
        public void put(Object obj) throws InterruptedException {
            //获得锁
            lock.lock();
            try {
                if (size >= maxSize) {
                    //如果队列满了就阻塞
                    condition.await();
                }
                this.obj.add(obj);
                LogUtils.d("put() called with: obj = [" + obj + "]" + this.obj.size());
                size++;
                //如果队列空了，添加元素后要通知take，唤醒condition
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public Object take() {
            lock.lock();
            try {
                if (size == 0) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object obj = this.obj.remove(0);
                LogUtils.i("take() called with: obj = [" + obj + "]" + this.obj.size());
                size--;
                condition.signal();
                return obj;
            } finally {
                lock.unlock();
            }
        }
    }
}
