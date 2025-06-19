package cn.devzyh.demo.factory;

import cn.devzyh.demo.factory.impl.AlibabaRedisAdapter;
import cn.devzyh.demo.factory.impl.TencentRedisAdapter;
import cn.devzyh.demo.util.RedisUtil;
import org.junit.Test;

public class JdkProxyFactoryTest {

    @Test
    public void getRedisProxy() {
        // 改造前的调用不影响
        RedisUtil util = new RedisUtil();
        util.get("key");
        System.out.println("-------------------");
        util.set("key", "val");
        System.out.println("-------------------");
        util.del("key");
        System.out.println("===================");

        // 万一我想改成调用腾讯云的Redis
        IRedisAdapter tencent = JdkProxyFactory.getRedisProxy(new TencentRedisAdapter());
        tencent.get("key");
        System.out.println("-------------------");
        tencent.set("key", "val");
        System.out.println("-------------------");
        tencent.del("key");
        System.out.println("===================");

        // 有的又想改成阿里云的Redis
        IRedisAdapter alibaba = JdkProxyFactory.getRedisProxy(new AlibabaRedisAdapter());
        alibaba.get("key");
        System.out.println("-------------------");
        alibaba.set("key", "val");
        System.out.println("-------------------");
        alibaba.del("key");
    }
}
