package cn.devzyh.demo.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * 创建线程的方式
 */
public class CreateThreadTest {

    public static void main(String[] args) {

//        byThreadTest();
//        byRunnableTest();
//        byCallableTest();
        byTimerTest();
    }

    /**
     * 重写Thread类的run方法
     */
    static void byThreadTest() {
        Thread th = new Thread() {
            @Override
            public void run() {
                System.out.println("我重写了Thread类的run方法");
                System.out.println("Thread Name = " + Thread.currentThread().getName());
            }
        };
        System.out.println("Main Thread = " + Thread.currentThread().getName());
        th.start();
    }

    /**
     * 实现Runnable接口
     */
    static void byRunnableTest() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我实现了Runnable接口");
                System.out.println("Thread Name = " + Thread.currentThread().getName());
            }
        });
        System.out.println("Main Thread = " + Thread.currentThread().getName());
        th.start();
    }

    /**
     * 实现Callable接口并获取返回值
     */
    static void byCallableTest() {
        // 实现 Callable 接口
        Callable cal = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("我实现了Callable接口");
                System.out.println("Thread Name = " + Thread.currentThread().getName());
                Thread.sleep(3000);
                return "hello concurrent";
            }
        };

        // 通过线程池执行并获取返回值
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future future = service.submit(cal);

        try {
            // 输出结果 未获取到前会阻塞程序
            System.out.println("result = " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过定时器实现
     */
    static void byTimerTest() {
        // 定义执行的任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("我是定时任务，我被执行了一次");
            }
        };

        // 延迟一秒后开始执行任务，然后每秒执行一次定时任务
        Timer timer = new Timer();
        timer.schedule(task, 1000, 1000);

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
