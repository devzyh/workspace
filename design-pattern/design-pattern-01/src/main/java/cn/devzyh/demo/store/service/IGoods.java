package cn.devzyh.demo.store.service;

import java.util.Map;

/**
 * 商品接口
 */
public interface IGoods {

    /**
     * 兑奖方法
     *
     * @param cdk        兑换码
     * @param extraPrams 额外参数
     */
    void redeem(String cdk, Map extraPrams);

}
