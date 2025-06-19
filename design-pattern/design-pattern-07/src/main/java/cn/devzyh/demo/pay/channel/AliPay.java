package cn.devzyh.demo.pay.channel;

import cn.devzyh.demo.pay.mode.IPayMode;

/**
 * 支付宝支付
 */
public class AliPay extends Pay {

    public AliPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public void pay(String uid, String tid) {
        System.out.println("使用支付宝支付");
        super.payMode.auth(uid);
    }
}
