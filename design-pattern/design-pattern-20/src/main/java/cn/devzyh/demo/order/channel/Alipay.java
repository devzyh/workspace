package cn.devzyh.demo.order.channel;

import cn.devzyh.demo.order.IPayChannel;

import java.math.BigDecimal;

public class Alipay implements IPayChannel {
    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("支付宝支付：" + amount + "元");
        return true;
    }

    @Override
    public BigDecimal total(BigDecimal amount) {
        return amount.add(BigDecimal.valueOf(1L));
    }
}
