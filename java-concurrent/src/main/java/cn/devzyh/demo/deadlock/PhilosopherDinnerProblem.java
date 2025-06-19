package cn.devzyh.demo.deadlock;

/**
 * 哲学家就餐问题
 * 问题条件：
 * 1. 一桌哲学家，坐在一起不停的吃饭或者思考
 * 2. 每人一个餐具，（必须筹够一副才能使用，比如一只筷子）
 * 3. 吃饭时必须拥有一副餐具，可以去拿左右人的餐具
 * 4. 每个人吃饭和思考时间不一样
 * <p>
 * 解决问题：
 * 如何避免每个人拿到一个餐具，又在等待别人手中餐具的情况。
 * 问题本质：
 * 线程环形依赖导致的死锁问题
 */
public class PhilosopherDinnerProblem {

    /**
     * 哲学家对象
     */
    static class Philosopher implements Runnable {
        private int id; // 编号
        private Object left; // 左边餐具
        private Object right; // 右边餐具

        public Philosopher(int id, Object left, Object right) {
            this.id = id;
            this.left = left;
            this.right = right;
        }

        /**
         * 模拟做事过程
         *
         * @param action
         */
        void doAction(String action) {
            System.out.println((System.currentTimeMillis() / 100) + "：ID为" + this.id + "的" + action);
            try {
                Thread.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("哲学家开始思考");
                    synchronized (left) {
                        doAction("哲学家拿起第一个餐具");
                        synchronized (right) {
                            doAction("哲学家拿起第二个餐具");
                            doAction("哲学家开始吃饭");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int maxSize = 5;
        // 创建指定数量的餐具
        Object[] objects = new Object[maxSize];
        // 初始化餐具对象
        for (int i = 0; i < maxSize; i++) {
            objects[i] = new Object();
        }

        // 创建线程传入哲学家对象并启动任务
        for (int i = 0; i < maxSize; i++) {
            Object left = objects[i];
            Object right = objects[(i + 1) % maxSize];
            if (i < maxSize - 1) {
                // 其他哲学家正常顺序拿餐具
                new Thread(new Philosopher(i + 1, left, right)).start();
            } else {
                // 调换最后一个哲学家拿餐具的顺序，避免死锁情况发生
                new Thread(new Philosopher(i + 1, right, left)).start();
            }
        }
    }
}
