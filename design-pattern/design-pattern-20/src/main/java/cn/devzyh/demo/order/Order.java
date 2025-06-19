package cn.devzyh.demo.order;

import java.math.BigDecimal;

/**
 * 订单操作
 */
public class Order {

    // 金额
    private BigDecimal amount;

    // 备注
    private String remark;

    /**
     * 获取支付总金额
     *
     * @param channel
     * @return
     */
    public BigDecimal caclPayTotal(IPayChannel channel) {
        this.amount = channel.total(this.amount);
        return this.amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
