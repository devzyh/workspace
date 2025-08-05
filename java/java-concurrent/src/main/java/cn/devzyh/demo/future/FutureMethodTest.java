package cn.devzyh.demo.future;

import java.util.concurrent.*;

/**
 * Future类相关方法使用，获取线程方法返回值
 *
 * @author devzyh
 */
public class FutureMethodTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> future = service.submit(new Task());

        try {
            future.cancel(false); // 线程任务取消执行，参数决定是否向任务线程发生中断信号

            // 任务取消成功返回true
            // 任务不允许中断将会直接返回false
            System.out.println("任务是否取消：" + future.isCancelled());

            // 抛出异常时也会返回true，返回false代表还没执行完成
            System.out.println("任务是否执行完成：" + future.isDone());

            // 获取任务返回值，不指定超时时间则一直等待
            System.out.println("线程返回值：" + future.get(3, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            // 线程任务发生异常，同意在获取结果是返回ExecutionException类型异常
            System.out.println("线程返回异常：" + e.getMessage());
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("获取值等待超时");
            e.printStackTrace();
        }

        service.shutdown();
    }


    /**
     * 具有返回值的线程任务
     */
    static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            // throw new NullPointerException("NPE");
            Thread.sleep(10000);
            return "hello world";
        }
    }
}



