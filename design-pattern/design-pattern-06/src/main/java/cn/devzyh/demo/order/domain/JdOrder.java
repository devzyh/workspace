package cn.devzyh.demo.order.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 京东订单信息
 */
public class JdOrder {
    private Long jid; // 单号
    private BigDecimal amount; // 金额
    private LocalDateTime payTime; // 时间

    public Long getJid() {
        return jid;
    }

    public void setJid(Long jid) {
        this.jid = jid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }
}
