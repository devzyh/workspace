package cn.devzyh.demo.sso.decorator;

import cn.devzyh.demo.sso.HandlerIntercept;

/**
 * 单点登录功能要装饰的角色
 */
public class SsoDecorator implements HandlerIntercept {

    private HandlerIntercept handlerIntercept;

    public SsoDecorator() {
    }

    public SsoDecorator(HandlerIntercept handlerIntercept) {
        this.handlerIntercept = handlerIntercept;
    }

    @Override
    public boolean preHandler(String url, Object req, Object resp) {
        return this.handlerIntercept.preHandler(url, req, resp);
    }

}
