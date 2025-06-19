package cn.devzyh.demo.pay.channel;

import cn.devzyh.demo.pay.mode.IPayMode;

/**
 * 支付业务抽象类
 * 支付=支付渠道+支付模式
 */
public abstract class Pay {

    // 支付模式
    protected IPayMode payMode;

    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    /**
     * 支付动作
     *
     * @param uid 用户编码
     * @param tid 交易编码
     */
    public abstract void pay(String uid, String tid);

}
