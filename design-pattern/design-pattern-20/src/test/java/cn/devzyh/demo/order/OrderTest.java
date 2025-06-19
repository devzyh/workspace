package cn.devzyh.demo.order;

import cn.devzyh.demo.order.channel.Alipay;
import cn.devzyh.demo.order.channel.Douyin;
import cn.devzyh.demo.order.channel.Weixin;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class OrderTest {

    @Test
    public void run() throws IOException {
        Order order = new Order();
        // 获取支付总金额
        order.setAmount(BigDecimal.valueOf(23.5D));
        IPayChannel channel = null;
        int num = 3;
        switch (num) {
            case 1:
                channel = new Alipay();
                break;
            case 2:
                channel = new Douyin();
                break;
            default:
                channel = new Weixin();
                break;
        }

        order.caclPayTotal(channel);
        // 支付
        channel.pay(order.getAmount());
    }
}
