package cn.devzyh.demo.deadlock;

/**
 * 避免线程死锁方法
 * 1. 代码避免，获取多个锁时，固定顺序获取，本示例演示这种方法。
 * 2. 检测恢复，发生死锁时，根据死锁检测算法，释放指定线程资源，解开死锁，具体释放根据业务而定。
 * 3. 鸵鸟策略，无视死锁的发生，发生时重启服务，内部系统多用这种方法。
 */
public class AvoidDeadLockTest {

    /**
     * 账户类
     */
    static class Account {
        private int id;
        private double balance;

        public Account(int id, double balance) {
            this.id = id;
            this.balance = balance;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }

    /**
     * 转账任务
     */
    static class TransferTask implements Runnable {
        Account from;
        Account to;
        Double money;

        public TransferTask(Account from, Account to, Double money) {
            this.from = from;
            this.to = to;
            this.money = money;
        }

        @Override
        public void run() {
            // 根据ID顺序固定锁获取顺序，避免死锁。
            Account a = from;
            Account b = to;
            if (from.id > to.id) {
                a = to;
                b = from;
            }

            synchronized (a) {
                try {
                    // 模拟系统延迟
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    if (from.balance - money < 0) {
                        System.out.println("账户余额不足，无法转出");
                    } else {
                        from.balance -= money;
                        to.balance += money;
                        System.out.printf("账户[%s]成功向账户[%s]转账[%f]元\n", from.id, to.id, money);
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1, 200);
        Account b = new Account(2, 200);

        Thread t1 = new Thread(new TransferTask(a, b, 100D));
        Thread t2 = new Thread(new TransferTask(b, a, 100D));
        Thread t3 = new Thread(new TransferTask(a, b, 210D));
        Thread t4 = new Thread(new TransferTask(a, b, 100D));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("账户a余额：" + a.balance);
        System.out.println("账户b余额：" + b.balance);
    }
}
