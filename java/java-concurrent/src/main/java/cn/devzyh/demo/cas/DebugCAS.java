package cn.devzyh.demo.cas;

/**
 * 模拟调试CAS过程
 */
public class DebugCAS {

    public static void main(String[] args) throws InterruptedException {
        SimulationCAS simulationCAS = new SimulationCAS(100);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                simulationCAS.compareAndSwap(100, 150);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("simulationCAS = " + simulationCAS.getValue());
    }

    /**
     * 模拟CAS类
     */
    static class SimulationCAS {
        private int value;

        public SimulationCAS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        /**
         * CAS操作
         *
         * @param expectedValue 期望值
         * @param newValue      新值
         * @return 旧值
         */
        public synchronized int compareAndSwap(int expectedValue, int newValue) {
            int oldValue = this.value;
            if (expectedValue == oldValue) {
                this.value = newValue;
                System.out.println("线程[" + Thread.currentThread().getName() + "]CAS操作成功");
            }
            return oldValue;
        }
    }
}
