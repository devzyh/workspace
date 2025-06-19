package cn.devzyh.demo.pay.channel;

import cn.devzyh.demo.pay.mode.IPayMode;

/**
 * 微信支付
 */
public class WxPay extends Pay {

    public WxPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public void pay(String uid, String tid) {
        System.out.println("使用微信支付");
        super.payMode.auth(uid);
    }
}
