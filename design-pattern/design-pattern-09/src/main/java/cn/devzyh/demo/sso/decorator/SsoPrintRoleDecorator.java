package cn.devzyh.demo.sso.decorator;

import cn.devzyh.demo.sso.HandlerIntercept;

/**
 * 装饰打印角色信息功能实现
 */
public class SsoPrintRoleDecorator extends SsoDecorator {

    public SsoPrintRoleDecorator() {
    }

    public SsoPrintRoleDecorator(HandlerIntercept handlerIntercept) {
        super(handlerIntercept);
    }

    @Override
    public boolean preHandler(String url, Object req, Object resp) {
        if (req.toString().contains("admin")){
            System.out.println("当前登录是管理员");
        }else {
            System.out.println("当前登录是普通用户");
        }
        return super.preHandler(url, req, resp);
    }
}
