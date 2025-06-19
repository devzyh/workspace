package cn.devzyh.demo.store.service.impl;

import cn.devzyh.demo.store.service.IGoods;

import java.util.Map;

/**
 * 优惠劵兑换实现类
 */
public class CouponGoodsService implements IGoods {

    @Override
    public void redeem(String cdk, Map extraPrams) {
        System.out.println("优惠劵兑换成功：" + cdk);
    }
}
