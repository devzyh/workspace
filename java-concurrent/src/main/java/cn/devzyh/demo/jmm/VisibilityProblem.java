package cn.devzyh.demo.jmm;

/**
 * 内存可见性问题
 */
public class VisibilityProblem {

    int a = 10;
    int b = 20;

    void modify() {
        a = 30;
        b = a;
    }

    void print() {
        System.out.println("a=" + a + ";b=" + b);
    }

    public static void main(String[] args) {

        while (true) {
            VisibilityProblem problem = new VisibilityProblem();
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                problem.modify();
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                problem.print();
            }).start();
        }

        // a=30;b=30 线程1先执行
        // a=10;b=20 线程2先执行
        // a=30;b=20 交叉执行，b发生可见性问题
        // a=10;b=30 交叉执行，a发生可见性问题
    }
}
