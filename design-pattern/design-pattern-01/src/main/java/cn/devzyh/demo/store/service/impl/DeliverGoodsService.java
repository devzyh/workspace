package cn.devzyh.demo.store.service.impl;

import cn.devzyh.demo.store.service.IGoods;

import java.util.Map;

/**
 * 实物兑换实现类
 */
public class DeliverGoodsService implements IGoods {

    @Override
    public void redeem(String cdk, Map extraPrams) {
        System.out.println("实物兑换成功：" + cdk);
        System.out.println("发货地址：" + extraPrams.get("address"));
    }
}
