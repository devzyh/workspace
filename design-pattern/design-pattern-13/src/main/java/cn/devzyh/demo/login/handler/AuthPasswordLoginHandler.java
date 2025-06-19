package cn.devzyh.demo.login.handler;

import cn.devzyh.demo.login.LoginHandler;

import java.util.Map;

/**
 * 密码验证登录处理器
 */
public class AuthPasswordLoginHandler extends LoginHandler {

    @Override
    public boolean check(Map req) {
        if (!req.get("pwd").equals("123456")) {
            return false;
        }

        System.out.println("密码验证通过");
        return next(req);
    }

}
