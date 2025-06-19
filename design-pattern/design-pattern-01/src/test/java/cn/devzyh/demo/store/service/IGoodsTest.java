package cn.devzyh.demo.store.service;

import cn.devzyh.demo.store.factory.StoreFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class IGoodsTest {

    @Test
    public void redeem() throws Exception {
        // 优惠劵发放
        IGoods goodsService = StoreFactory.getGoodsService(1);
        goodsService.redeem("1232131231", null);

        // 实物发放
        goodsService = StoreFactory.getGoodsService(2);
        Map<String, String> params = new HashMap<>();
        params.put("address", "湖北武汉");
        goodsService.redeem("321312321312", params);

        // 错误的类型
        // goodsService = StoreFactory.getGoodsService(9);
    }
}
