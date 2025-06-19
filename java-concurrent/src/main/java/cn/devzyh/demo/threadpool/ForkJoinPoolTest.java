package cn.devzyh.demo.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分裂汇总任务的计算
 * 适合任务可拆分成多个子任务并行计算的场景
 */
public class ForkJoinPoolTest {

    /**
     * 斐波那契数列分裂合并计算子类
     */
    static class FibonacciTask extends RecursiveTask<Integer> {
        private Integer num;

        public FibonacciTask(Integer num) {
            this.num = num;
        }

        @Override
        protected Integer compute() {
            // 数字计算到0时计算结束
            if (this.num <= 1) {
                return this.num;
            }

            // 分裂第一个组成数字计算
            FibonacciTask f1 = new FibonacciTask(this.num - 1);
            f1.fork();

            // 分裂第二个组成数字计算
            FibonacciTask f2 = new FibonacciTask(this.num - 2);
            f2.fork();

            // 返回分裂后的计算结果
            return f1.join() + f2.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = pool.submit(new FibonacciTask(10));
        try {
            System.out.println("第10个斐波那契数值是：" + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
