package cn.devzyh.demo.api;

/**
 * API功能抽象类
 */
public abstract class AbstractApi {

    /**
     * 获取指定API数据
     *
     * @param key
     * @param secret
     * @param api
     * @return
     */
    public String getApiData(String key, String secret, String api) {
        String token = auth(key, secret);
        return getData(token, api);
    }

    /**
     * 获取APItoken
     *
     * @param key
     * @param secret
     * @return
     */
    protected abstract String auth(String key, String secret);

    /**
     * 获取指定API数据
     *
     * @param token
     * @param api
     * @return
     */
    protected abstract String getData(String token, String api);

}
