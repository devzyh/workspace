package cn.devzyh.demo.container;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap线程不安全验证
 *
 * 扩容期间取数不正确
 * 两个线程put同样的key可能导致数据不一致
 * 扩容时会产生CPU跑满的情况
 *
 * @author devzyh
 */
public class HashMapThreadUnsafeTest {

    public static void main(String[] args) {
        // 创建HashMap
        Map<Integer, String> map = new HashMap<>();
        int maxKey = 999999;
        map.put(maxKey, "value"); // 确保maxKey存在

        // 另起线程不停的添加数据，确保HashMap出现扩容情况
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < maxKey; i++) {
                    map.put(i, "value");
                }
            }
        }).start();

        // 当前线程不停的取数，确认是否会出现扩容期间取数不正确的情况
        while (true) {
            if (map.get(maxKey) == null) {
                System.out.println("HashMap取数出现异常");
                break;
            }
        }
    }
}
