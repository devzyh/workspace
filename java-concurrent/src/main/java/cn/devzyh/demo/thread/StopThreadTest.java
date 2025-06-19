package cn.devzyh.demo.thread;

/**
 * 优雅的停止线程
 */
public class StopThreadTest {

    /**
     * 通过中断信号来停止线程
     * 线程会抛出线程中断异常的时候，不要屏蔽，要手动处理信号或向上抛出异常
     */
    public static void main(String[] args) throws InterruptedException {

        Thread th = new Thread(new Runnable() {
            int num = 0;

            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted() && num < 1000) {
                    System.out.println("num = " + num++);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // 响应到中断信号后会抛出中断异常并清除中断信号
                        // 再次手动发起中断信号确保下次循环可以响应线程退出
                        Thread.currentThread().interrupt();
                        System.out.println("sleep已接收到中断信号");
                    }
                }
                System.out.println("我们要安全优雅的停止线程的运行");
            }
        });

        th.start();
        Thread.sleep(500);
        th.interrupt();
    }

    /**
     * 其他中断异常的方法：
     * stop()方法会直接终止线程的运行不会进行任何处理，已废弃
     * suspend()与resume()方法会导致线程产生死锁，即锁不释放直接中断线程，已废弃
     * volatile修饰的线程终止标记变量，在阻塞或线程休眠情况下会不响应从而导致中断判断失效
     */
}
