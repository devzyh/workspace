package cn.devzyh.demo.pay.channel;

import cn.devzyh.demo.pay.mode.FingerprintPayModeImpl;
import cn.devzyh.demo.pay.mode.PasswordPayModeImpl;
import org.junit.Test;

public class PayTest {

    @Test
    public void pay() {
        // 测试支付宝指纹支付
        Pay aliPay = new AliPay(new FingerprintPayModeImpl());
        aliPay.pay("zhangsan", "1111111111");

        // 测试微信密码支付
        Pay wxPay = new WxPay(new PasswordPayModeImpl());
        wxPay.pay("lisi", "2222222222");
    }
}