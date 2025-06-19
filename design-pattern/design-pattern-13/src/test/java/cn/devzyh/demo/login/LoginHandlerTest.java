package cn.devzyh.demo.login;

import cn.devzyh.demo.login.handler.AuthPasswordLoginHandler;
import cn.devzyh.demo.login.handler.CheckAdminLoginHandler;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LoginHandlerTest {

    @Test
    public void login() {
        LoginHandler login = LoginHandler.link(
                new AuthPasswordLoginHandler(),
                new CheckAdminLoginHandler());

        Map<String, String> req = new HashMap<>();
        req.put("pwd", "123456");
        req.put("role", "user");

        // 非管理员登录
        System.out.println(login.check(req));

        // 管理员登录
        req.put("role", "admin");
        System.out.println(login.check(req));
    }

}