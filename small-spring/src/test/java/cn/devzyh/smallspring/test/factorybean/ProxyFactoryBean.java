package cn.devzyh.smallspring.test.factorybean;

import cn.devzyh.smallspring.beans.BeansException;
import cn.devzyh.smallspring.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 代理对象实现，这里演示代理INameDao
 */
public class ProxyFactoryBean implements FactoryBean<INameDao> {

    @Override
    public INameDao getObject() throws BeansException {
        // 这里要使用动态代理创建对应的对象
        InvocationHandler handler = (proxy, method, args) -> {
            // 添加排除方法
            if ("toString".equals(method.getName())) {
                return this.toString();
            }

            // 实现被代理对象的功能
            Map<Integer, String> names = new HashMap<>();
            names.put(1, "张三");
            names.put(2, "李四");
            names.put(3, "王五");
            return "被代理对象：" + method.getName() + ":" + names.get((Integer) args[0]);
        };
        // 创建代理对象
        return (INameDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{INameDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return INameDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
