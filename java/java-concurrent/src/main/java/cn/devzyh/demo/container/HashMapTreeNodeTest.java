package cn.devzyh.demo.container;

import java.util.HashMap;

/**
 * HashMap哈希桶中节点转换成红黑树的测试
 * <p>
 * HashMap在JDK8中：
 * 当链表长度大于或等于阈值（默认为 8）的时候，就会把链表转换为红黑树。
 * 如果由于删除或者其他原因调整了大小，当红黑树的节点小于或等于6个以后，又会恢复为链表形态。
 * 注意：当存储桶数组长度小于64（常量定义）的时候，会先扩张数组长度一倍，否则把桶中链表转为红黑树。
 * <p>
 * 转换红黑树正常情况下发生概率为千万分之六，极端情况下通过链表和红黑树的转换均衡速度与空间
 */
public class HashMapTreeNodeTest {

    public static void main(String[] args) {
        HashMap<SameHashCode, Integer> map = new HashMap<>(1);
        for (int i = 0; i < 100; i++) {
            SameHashCode shc = new SameHashCode();
            map.put(shc, 1);
        }
        SameHashCode shc = new SameHashCode();
        map.put(shc, 1); // 此处打断点进入观察Map内部结构是否为红黑树
        System.out.println(map.size());
    }

    static class SameHashCode {
        @Override
        public int hashCode() {
            // 确保对象返回同样的哈希值
            return 1;
        }
    }
    
}
