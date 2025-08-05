package cn.devzyh.demo.proxy.manual.injection.impl;

import cn.devzyh.demo.proxy.manual.injection.IPersonService;

/**
 * 代理类
 * 这里代理人员服务实现类，并对方法做一些增强
 */
public class PersonProxyServiceImpl implements IPersonService {

    /**
     * 被代理类的实例
     */
    private IPersonService target;

    /**
     * 构造代理对象时传入被代理对象实例
     *
     * @param target
     */
    public PersonProxyServiceImpl(IPersonService target) {
        this.target = target;
    }

    @Override
    public void run() {
        System.out.println("跑步前要热身，不然容易受伤");

        // 执行被代理对象的方法
        this.target.run();

        System.out.println("跑步后要大步走，放松下肌肉");
    }


    /**
     * 静态代理组合方式实现的测试类
     *
     * @param args
     */
    public static void main(String[] args) {
        PersonServiceImpl target = new PersonServiceImpl();
        PersonProxyServiceImpl proxy = new PersonProxyServiceImpl(target);
        // 调用代理对象的方法
        proxy.run();
    }
}
