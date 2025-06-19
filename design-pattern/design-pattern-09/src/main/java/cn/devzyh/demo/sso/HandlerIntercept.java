package cn.devzyh.demo.sso;

/**
 * 模拟Spring拦截处理器接口
 */
public interface HandlerIntercept {

    /**
     * 请求前置处理
     * @return
     */
    boolean preHandler(String url, Object req, Object resp);

}
