package cn.devzyh.demo.order.domain;

import java.time.LocalDateTime;

/**
 * 小红书订单信息
 */
public class RedOrder {
    private String orderCode; // 单号
    private Long money; // 金额
    private LocalDateTime createdTime; // 时间

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
