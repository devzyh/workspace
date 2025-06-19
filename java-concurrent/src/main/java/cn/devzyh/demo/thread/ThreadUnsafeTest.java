package cn.devzyh.demo.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程不安全的例子
 */
public class ThreadUnsafeTest {

    // volatile只能保证内存可见性
    volatile static int i = 0;

    /**
     * 多个线程操作同一个变量导致的运行结果错误
     * 预计结果：线程数*1000，实际结果：可能小于预计结果
     */
    static void resultErrorTest() throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10000; j++) {
                    i++; // i = i + 1; 不是原子操作
                }
            }
        };

        new Thread(r).start();
        new Thread(r).start();
        Thread t = new Thread(r);
        t.start();
        t.join(); // 等待结果返回
        System.out.printf("i = " + i); // 输出结果可能小于30000
    }

    /**
     * 代码执行时序引发的线程不安全问题
     * 新线程中初始化对象需要时间，立马获取数据会产生异常
     */
    static void initErrorTest() {
        // 测试类
        class Test {
            private Map<String, String> map;

            public Test() {
                // 在新线程中对对象初始化
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        map = new HashMap();
                        map.put("key", "value");
                    }
                }).start();
            }

            public String getVal(String key) {
                return map.get(key);
            }
        }

        Test test = new Test(); // 触发空指针异常
        System.out.printf("value = " + test.getVal("key"));
    }

    /**
     * 死锁，锁直接互相等待对方释放锁导致程序无法向下运行
     * 除此之外还有活锁（例如无限重试造成的问题）、饥饿（例如因线程占用锁导致其他线程无法获取到被锁的资源）
     */
    static void deadLockTest() throws InterruptedException {
        // 测试类
        class Test {
            Object obj1 = new Object();
            Object obj2 = new Object();

            public void thread1() throws InterruptedException {
                synchronized (obj1) {
                    System.out.println("thread1已独占obj1对象");
                    Thread.sleep(300);
                    synchronized (obj2) {
                        // 程序将陷入死锁无法运行到这一步
                        System.out.println("thread1已独占obj2对象");
                    }
                }
            }

            public void thread2() throws InterruptedException {
                synchronized (obj2) {
                    System.out.println("thread2已独占obj2对象");
                    Thread.sleep(300);
                    synchronized (obj1) {
                        // 程序将陷入死锁无法运行到这一步
                        System.out.println("thread2已独占obj1对象");
                    }
                }
            }
        }

        Test test = new Test();
        new Thread(() -> {
            try {
                test.thread1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                test.thread2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("线程启动完成");
    }

    public static void main(String[] args) throws Exception {
//        resultErrorTest();
//        initErrorTest();
        deadLockTest();
    }
}
