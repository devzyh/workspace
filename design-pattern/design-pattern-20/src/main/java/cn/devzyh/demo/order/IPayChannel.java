package cn.devzyh.demo.order;

import java.math.BigDecimal;

/**
 * 支付渠道接口
 */
public interface IPayChannel {

    /**
     * 支付动作
     *
     * @param amount
     */
    boolean pay(BigDecimal amount);

    /**
     * 支付总金额
     */
    BigDecimal total(BigDecimal amount);
}
