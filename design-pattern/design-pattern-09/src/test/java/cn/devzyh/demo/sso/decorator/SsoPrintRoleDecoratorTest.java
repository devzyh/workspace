package cn.devzyh.demo.sso.decorator;

import cn.devzyh.demo.sso.HandlerIntercept;
import cn.devzyh.demo.sso.intercept.SsoHandlerIntercept;
import org.junit.Test;

public class SsoPrintRoleDecoratorTest {

    @Test
    public void preHandler() {
        /**
         * 给单点登录功能装饰上打印角色信息的功能
         */
        HandlerIntercept handlerIntercept = new SsoPrintRoleDecorator(new SsoHandlerIntercept());
        handlerIntercept.preHandler("/login", "admin", null);

        handlerIntercept.preHandler("/info", "user", null);
    }
}