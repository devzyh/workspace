package cn.devzyh.demo.order.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 淘宝订单信息
 */
public class TbOrder {

    private Long tradeId; // 单号
    private BigDecimal price; // 金额
    private LocalDateTime tradeTime; // 时间

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(LocalDateTime tradeTime) {
        this.tradeTime = tradeTime;
    }
}
