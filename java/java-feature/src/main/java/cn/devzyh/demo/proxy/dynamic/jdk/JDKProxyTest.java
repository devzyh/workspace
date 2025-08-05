package cn.devzyh.demo.proxy.dynamic.jdk;

import cn.devzyh.demo.proxy.dynamic.jdk.impl.PersonServiceImpl;

import java.lang.reflect.Proxy;

public class JDKProxyTest {

    public static void main(String[] args) {
        // 生成动态代理对象的class文件
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        
        // 被代理的目标对象
        PersonServiceImpl target = new PersonServiceImpl();
        // 获取类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 获取被代理对象的接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 指定代理对象的调用处理器
        PersonInvocationHandler handler = new PersonInvocationHandler(target);
        // 使用Proxy类动态创建代理对象
        IPersonService proxy = (IPersonService) Proxy.newProxyInstance(classLoader, interfaces, handler);
        // 调用代理对象的方法，这里的方法已经被增强过
        proxy.run();
        proxy.eat("香蕉");
        proxy.callSelf(1).callSelf(2).callSelf(3);
    }
}
