package cn.devzyh.demo.proxy.manual.injection.impl;

import cn.devzyh.demo.proxy.manual.injection.IPersonService;

/**
 * 被代理对象
 * 人员服务实现类
 */
public class PersonServiceImpl implements IPersonService {

    @Override
    public void run() {
        System.out.println("我在跑步，别烦我");
    }

}
