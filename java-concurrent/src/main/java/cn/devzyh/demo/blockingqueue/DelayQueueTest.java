package cn.devzyh.demo.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时阻塞队列测试
 */
public class DelayQueueTest {

    public static void main(String[] args) throws Exception {
        // 延迟队列
        // 内部结构使用堆，按照延时长短进行排序
        DelayQueue<DelayedTask> queue = new DelayQueue();
        queue.offer(new DelayedTask("task1", 10000L));
        queue.offer(new DelayedTask("task2", 3000L));
        queue.offer(new DelayedTask("task3", 2000L));
        queue.offer(new DelayedTask("task4", 5000L));
        queue.offer(new DelayedTask("task5", 8000L));
        queue.offer(new DelayedTask("task6", 6000L));
        queue.offer(new DelayedTask("task7", 4000L));

        while (true) {
            // 最先过期的数据将被返回
            DelayedTask m = queue.take();
            System.out.println(m.toString());
        }
    }

    /**
     * 延时任务
     */
    static class DelayedTask implements Delayed {

        private String taskName;
        private Long taskDelayTime;

        public DelayedTask(String taskName, Long timeout) {
            this.taskName = taskName;
            this.taskDelayTime = System.currentTimeMillis() + timeout;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            // 获取过期时间
            return unit.convert(taskDelayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            // 处理排序逻辑
            DelayedTask m = (DelayedTask) o;
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - m.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "MyDelayedTask{" +
                    "taskName='" + taskName + '\'' +
                    ", taskDelayTime=" + taskDelayTime +
                    '}';
        }
    }

}


