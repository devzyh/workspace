package cn.devzyh.demo.config;

/**
 * 程序配置信息
 * 备忘对象
 */
public class ConfigInfo {

    private String appKey;

    private String appPwd;

    private String appUrl;

    public ConfigInfo(String appKey, String appPwd, String appUrl) {
        this.appKey = appKey;
        this.appPwd = appPwd;
        this.appUrl = appUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppPwd() {
        return appPwd;
    }

    public String getAppUrl() {
        return appUrl;
    }
}
