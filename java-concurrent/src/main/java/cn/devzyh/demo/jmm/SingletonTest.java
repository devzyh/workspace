package cn.devzyh.demo.jmm;

/**
 * 单例模式测试
 */
public class SingletonTest {

    private static volatile Object object;

    public static Object getInstance() {

        // 第一次判断是为了防止多线程串行执行降低效率
        if (object == null) {
            // 异步锁是为了保证当前只有一个线程去执行
            synchronized (SingletonTest.class) {
                // 第二次判断是为了防止之前等待锁的线程再次初始化单例对象
                if (object == null) {
                    // object使用volatile修饰是为了避免重排序
                    // 重排序可能出现Object对象构造方法未执行完，就已经指向object。
                    // 此时新进来的线程会获取到这个未初始化完成的对象，可能出现异常
                    object = new Object();
                }
            }
        }

        return object;
    }
}
