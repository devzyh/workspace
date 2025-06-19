package cn.devzyh.demo.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;

public class CGLibProxyTest {

    public static void main(String[] args) {
        // 实例化增强类对象
        Enhancer enhancer = new Enhancer();
        // 设置需要代理的类型
        enhancer.setSuperclass(StudentService.class);
        // 设置方法拦截器的实现
        enhancer.setCallback(new StudentMethodInterceptor());
        // 动态创建代理对象，也就是被代理对象的子类
        StudentService proxy = (StudentService) enhancer.create();
        // 调用代理后的方法
        proxy.eat();
    }

}
