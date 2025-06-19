package cn.devzyh.demo.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 借助FutureTask将带有返回值的任务传递给Thread构造
 *
 * @author devzyh
 */
public class FutureTaskTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        new Thread(futureTask).start(); // 此处传递的时可被获取返回值的任务
        Thread.sleep(1000);

        System.out.println("任务运行结果：" + futureTask.get());
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return 999;
        }
    }
}

