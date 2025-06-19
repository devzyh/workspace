package cn.devzyh.demo.order.channel;

import cn.devzyh.demo.order.IPayChannel;

import java.math.BigDecimal;

public class Weixin implements IPayChannel {
    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("微信支付：" + amount + "元");
        return true;
    }

    @Override
    public BigDecimal total(BigDecimal amount) {
        return amount.add(BigDecimal.valueOf(1.5D));
    }
}
