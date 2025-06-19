package cn.devzyh.demo.store.factory;

import cn.devzyh.demo.store.service.IGoods;
import cn.devzyh.demo.store.service.impl.DeliverGoodsService;
import cn.devzyh.demo.store.service.impl.CouponGoodsService;

/**
 * 商店模块工厂方法
 */
public class StoreFactory {

    /**
     * 根据类型获取商品实现类
     *
     * @param type
     * @return
     * @throws Exception
     */
    public static IGoods getGoodsService(int type) throws Exception {
        switch (type) {
            case 1:
                return new CouponGoodsService();
            case 2:
                return new DeliverGoodsService();
            default:
                throw new Exception("不存在的类型");
        }
    }

}
