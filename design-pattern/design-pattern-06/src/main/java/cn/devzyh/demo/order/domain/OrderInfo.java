package cn.devzyh.demo.order.domain;

/**
 * 订单通用信息
 */
public class OrderInfo {

    String sourceOrderCode; // 单号
    String amount; // 金额
    String payTime; // 时间

    public String getSourceOrderCode() {
        return sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
        this.sourceOrderCode = sourceOrderCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
