package cn.devzyh.demo.proxy.dynamic.jdk;

/**
 * 人员服务类接口
 */
public interface IPersonService {

    /**
     * 跑步
     */
    void run();

    /**
     * 吃东西
     *
     * @param food
     * @return
     */
    String eat(String food);


    IPersonService callSelf(int num);

}
