package cn.devzyh.demo.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 带有版本号的原子类测试
 * 可以解决ABA问题，CAS时根据版本判断
 */
public class AtomicStampedReferenceTest {

    public static void main(String[] args) {
        AtomicStampedReference<Integer> reference = new AtomicStampedReference<Integer>(0, 0);

        /**
         * CAS操作
         *
         * 期望值
         * 修改后的值
         * 期望版本
         * 修改后的版本
         */
        reference.compareAndSet(0, 1, 0, 1);

        System.out.println("reference=" + reference.getReference());
        System.out.println("stamp=" + reference.getStamp());
    }
}
