package cn.devzyh.demo.login.handler;

import cn.devzyh.demo.login.LoginHandler;

import java.util.Map;

/**
 * 管理员身份验证处理器
 */
public class CheckAdminLoginHandler extends LoginHandler {

    @Override
    public boolean check(Map req) {
        if (!req.get("role").equals("admin")) {
            return false;
        }

        System.out.println("管理员身份验证通过");
        return next(req);
    }
}
