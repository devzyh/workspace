package cn.devzyh.demo.lock;

/**
 * Synchronized锁测试
 */
public class SynchronizedTest {

    static class SyncTest {

        /**
         * 同步代码块
         * 底层采用monitor锁实现
         * class文件反汇编后将会看到一个monitorenter命令以及两个monitorexit命令，两次对出对应正常和异常结束程序
         * 获取锁的线程上锁一次对应计数器+1释放锁则-1，计数器变为0时表示彻底释放别的线程可以获取到锁。
         */
        void syncBlock() {
            synchronized (this) {
                System.out.println("我是同步代码块");
            }
        }

        /**
         * 同步方法
         * 底层采用方法FLAG实现
         * 线程执行方法时会检查方法flags是否有ACC_SYNCHRONIZED关键字
         * 如果存在则进入方法前执行monitorenter执行完毕后执行monitorexit
         */
        synchronized void syncMethod() {

            System.out.println("我是同步方法");
        }
    }

    /**
     * 测试
     * class文件反汇编命令：javap -verbose SynchronizedTest$SyncTest.class
     *
     * @param args
     */
    public static void main(String[] args) {
        SyncTest st = new SyncTest();
        st.syncBlock();
        st.syncMethod();
    }
}