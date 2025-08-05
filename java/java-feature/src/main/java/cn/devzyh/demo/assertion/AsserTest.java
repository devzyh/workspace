package cn.devzyh.demo.assertion;

import java.util.Random;

public class AsserTest {

    public static void main(String[] args) {
        int num = new Random().nextInt(100);

        // 不满足断言条件会使程序抛出AssertionException异常并退出程序
        assert num > 50;

        // 断言还可以指定异常信息
        assert num <= 50 : "自定义异常信息";

        System.out.println(num);

        // 断言默认不开启，仅推荐在开发和测试阶段使用
        // 开启断言方式
        // JVM参数：-enableassertions 或者缩写 -ea

        // 对特定类使用断言
        // -ea:-ea:com.devzyh.demo.assertion.AsserTest

        // 对特定包使用断言
        // -ea:-ea:com.devzyh.demo...
    }
}
