package cn.devzyh.demo.api.api;

import cn.devzyh.demo.api.AbstractApi;

/**
 * 腾讯API实现
 */
public class TencentApi extends AbstractApi {
    @Override
    protected String auth(String key, String secret) {
        System.out.println("登录腾讯API平台成功");
        return "tencent:" + key + ":" + secret;
    }

    @Override
    protected String getData(String token, String api) {
        System.out.println("从腾讯API获取数据成功");
        return "token：" + token + ";api:" + api;
    }
}
