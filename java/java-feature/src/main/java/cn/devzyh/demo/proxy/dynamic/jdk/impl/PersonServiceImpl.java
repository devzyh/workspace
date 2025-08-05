package cn.devzyh.demo.proxy.dynamic.jdk.impl;


import cn.devzyh.demo.proxy.dynamic.jdk.IPersonService;

/**
 * 被代理对象
 * 人员服务实现类
 */
public class PersonServiceImpl implements IPersonService {

    @Override
    public void run() {
        System.out.println("我在跑步，别烦我");
    }

    @Override
    public String eat(String food) {
        String str = "我饿了，来吃点" + food;
        System.out.println(food);
        return food;
    }

    @Override
    public IPersonService callSelf(int num) {
        System.out.println("num=" + num);
        return this;
    }
}
