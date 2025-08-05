package cn.devzyh.demo.controller;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Util;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class OpenApiController {

    @RequestMapping("/api/in")
    public SaResult apiIn() {
        // 获取 Access-Token 对应的账号id
        String accessToken = SaHolder.getRequest().getHeader("Authorization");
        Object loginId = SaOAuth2Util.getLoginIdByAccessToken(accessToken);
        System.out.println("-------- 此Access-Token对应的账号id: " + loginId);

        // 校验 Access-Token 是否具有权限: userinfo
        SaOAuth2Util.checkScope(accessToken, "userinfo");

        // 模拟账号信息 （真实环境需要查询数据库获取信息）
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("nickname", "shengzhang_");
        map.put("avatar", "http://xxx.com/1.jpg");
        map.put("age", "18");
        map.put("sex", "男");
        map.put("address", "山东省 青岛市 城阳区");
        return SaResult.data(map);
    }

}