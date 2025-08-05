package cn.devzyh.demo.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本地线程对象测试
 */
public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {
        //threadUnsafe();

        threadSafe();
    }

    /**
     * 多个线程共用一个线程不安全的对象
     * 期望值不重复，实际重复，产生了线程安全问题
     */
    private static void threadUnsafe() {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

        // 使用更多的线程逼出问题
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            new Thread(() -> {
                Date date = new Date(1000 * finalI);
                // 下面的输出可能会出现重复值
                System.out.println(sdf.format(date));
            }).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 每个线程使用一个自己专属的线程不安全对象
     * 期望值不重复，实际不重复，不会产生线程安全问题
     */
    private static void threadSafe() {

        // 一个存储SimpleDateFormat的ThreadLocal容器
        ThreadLocal<SimpleDateFormat> localSDF = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                // 初始化本地线程对应的值
                return new SimpleDateFormat("mm:ss");
            }
        };

        // 线程池内的工作线程独享一个 SimpleDateForm 对象，既不会乱也不会浪费内存
        ExecutorService pool = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 1000; i++) {
            final int finalSecond = i;
            pool.submit(() -> {
                Date date = new Date(1000 * finalSecond);
                // 使用当前线程池独有的日期格式化对象
                System.out.println(localSDF.get().format(date));
            });
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }
}
