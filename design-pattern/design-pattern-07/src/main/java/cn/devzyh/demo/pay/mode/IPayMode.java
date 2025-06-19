package cn.devzyh.demo.pay.mode;

/**
 * 支付模式
 */
public interface IPayMode {

    /**
     * 支付验证动作
     *
     * @param uid
     */
    void auth(String uid);

}
