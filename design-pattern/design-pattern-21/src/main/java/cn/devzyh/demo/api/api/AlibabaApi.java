package cn.devzyh.demo.api.api;

import cn.devzyh.demo.api.AbstractApi;

/**
 * 阿里API实现
 */
public class AlibabaApi extends AbstractApi {
    @Override
    protected String auth(String key, String secret) {
        System.out.println("登录阿里API平台成功");
        return "alibaba:" + key + ":" + secret;
    }

    @Override
    protected String getData(String token, String api) {
        System.out.println("从阿里API获取数据成功");
        return "token：" + token + ";api:" + api;
    }
}
