package cn.devzyh.demo.order;

import cn.devzyh.demo.order.domain.JdOrder;
import cn.devzyh.demo.order.domain.RedOrder;
import cn.devzyh.demo.order.domain.TbOrder;
import com.alibaba.fastjson2.JSON;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class OrderInfoAdapterTest {

    @Test
    public void convert() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // 淘宝订单
        TbOrder tbOrder = new TbOrder();
        tbOrder.setTradeId(100000L);
        tbOrder.setPrice(BigDecimal.valueOf(300.12));
        tbOrder.setTradeTime(LocalDateTime.now());
        // 字段映射关系
        Map<String, String> tbMapping = new HashMap<>();
        tbMapping.put("sourceOrderCode", "tradeId");
        tbMapping.put("amount", "price");
        tbMapping.put("payTime", "tradeTime");
        // 适配前后数据
        System.out.println("淘宝适配前数据：" + JSON.toJSONString(tbOrder));
        System.out.println("淘宝适配后数据：" + JSON.toJSONString(OrderInfoAdapter
                .convert(JSON.toJSONString(tbOrder), tbMapping)));

        // 京东订单
        JdOrder jdOrder = new JdOrder();
        jdOrder.setJid(100000L);
        jdOrder.setAmount(BigDecimal.valueOf(300.12));
        jdOrder.setPayTime(LocalDateTime.now());
        // 字段映射关系
        Map<String, String> jdMapping = new HashMap<>();
        jdMapping.put("sourceOrderCode", "jid");
        jdMapping.put("amount", "amount");
        jdMapping.put("payTime", "payTime");
        // 适配前后数据
        System.out.println("京东适配前数据：" + JSON.toJSONString(jdOrder));
        System.out.println("京东适配后数据：" + JSON.toJSONString(OrderInfoAdapter
                .convert(JSON.toJSONString(jdOrder), jdMapping)));

        // 小红书订单
        RedOrder redOrder = new RedOrder();
        redOrder.setOrderCode("100000");
        redOrder.setMoney(30012L);
        redOrder.setCreatedTime(LocalDateTime.now());
        // 字段映射关系
        Map<String, String> redMapping = new HashMap<>();
        redMapping.put("sourceOrderCode", "orderCode");
        redMapping.put("amount", "money");
        redMapping.put("payTime", "createdTime");
        // 适配前后数据
        System.out.println("小红书适配前数据：" + JSON.toJSONString(redOrder));
        System.out.println("小红书适配后数据：" + JSON.toJSONString(OrderInfoAdapter
                .convert(JSON.toJSONString(redOrder), redMapping)));
    }
}