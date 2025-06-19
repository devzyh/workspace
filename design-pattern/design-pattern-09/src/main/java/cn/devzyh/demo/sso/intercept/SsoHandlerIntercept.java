package cn.devzyh.demo.sso.intercept;

import cn.devzyh.demo.sso.HandlerIntercept;

/**
 * 单点的登陆拦截器
 */
public class SsoHandlerIntercept implements HandlerIntercept {

    @Override
    public boolean preHandler(String url, Object req, Object resp) {
        System.out.printf("%s登陆%s成功\n", req, url);
        return true;
    }

}
