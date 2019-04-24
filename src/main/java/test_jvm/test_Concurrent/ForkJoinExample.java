package test_jvm.test_Concurrent;

import utils.LogUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample extends RecursiveTask<Integer> {
    private final int threhold = 100;
    private int first;
    private int last;
    private int level;

    public ForkJoinExample(int first, int last) {
        this(first, last, 0);
    }

    public ForkJoinExample(int first, int last, int level) {
        this.first = first;
        this.last = last;
        this.level = level;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last - first <= threhold) {
            // 任务足够小则直接计算
            for (int i = first; i <= last; i++) {
                result += i;
            }
        } else {
            // 拆分成小任务
            int middle = first + (last - first) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(first, middle, level - 1);
            ForkJoinExample rightTask = new ForkJoinExample(middle + 1, last, level - 1);
            leftTask.fork();
            rightTask.fork();
            result = leftTask.join() + rightTask.join();

            LogUtils.log("compute() "
                            + String.format("count:%d  [%d,%d] + [%d,%d] => [%d,%d]:", middle - first + 1, first, middle, middle + 1, last, first, last)
                            + "result:" + result
                    , level);
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinExample example = new ForkJoinExample(1, 10000, -1);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future result = forkJoinPool.submit(example);
        LogUtils.log(result.get(), 5);
    }
}

