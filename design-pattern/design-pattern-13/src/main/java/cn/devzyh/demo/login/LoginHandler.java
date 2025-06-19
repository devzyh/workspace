package cn.devzyh.demo.login;

import java.util.Map;

/**
 * 登录校验处理器抽象类
 */
public abstract class LoginHandler {

    // 指向下一个登录处理器
    private LoginHandler nextHandler;

    /**
     * 连接登录处理器执行链路
     *
     * @param first  第一个处理
     * @param chains 其他处理器
     * @return
     */
    public static LoginHandler link(LoginHandler first, LoginHandler... chains) {
        LoginHandler handler = first;
        for (LoginHandler chain : chains) {
            if (chain != null) {
                handler.nextHandler = chain;
                handler = chain;
            }
        }
        return first;
    }

    /**
     * 登录请求处理器校验方法
     *
     * @param req
     * @return
     */
    public abstract boolean check(Map req);

    /**
     * 执行下一个校验器
     *
     * @param req
     * @return
     */
    protected boolean next(Map req) {
        if (nextHandler == null) {
            // 校验结束 通过
            return true;
        }
        return nextHandler.check(req);
    }

}
