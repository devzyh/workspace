package cn.devzyh.smallspring.aop.framework;

import cn.devzyh.smallspring.aop.AdvisedSupport;
import cn.devzyh.smallspring.aop.AopProxy;

public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createProxy().getProxy();
    }

    /**
     * 创建代理类对象
     *
     * @return
     */
    private AopProxy createProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }
}
