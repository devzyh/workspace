package cn.devzyh.demo.jmm;

/**
 * happen-before原则
 */
public class HappenBefore {

    /**
     * 线程A解锁前所有操作 happen-before 获取同一把锁的线程B里的所有操作
     *
     * volatile修饰的变量，修改操作 happen-before 读取操作
     *
     * 线程A内所有操作 happen-before 线程A join 后的语句
     */
}
