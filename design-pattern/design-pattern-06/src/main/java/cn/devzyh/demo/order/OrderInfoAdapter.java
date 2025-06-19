package cn.devzyh.demo.order;

import cn.devzyh.demo.order.domain.OrderInfo;
import com.alibaba.fastjson2.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 订单信息适配器
 */
public class OrderInfoAdapter {

    /**
     * 订单信息转换器
     *
     * @param json    原始值JSON串
     * @param mapping 字段映射关系（项目中这块可以考虑作为动态配置）
     * @return
     */
    public static OrderInfo convert(String json, Map<String, String> mapping)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map map = (Map) JSONObject.parseObject(json);
        return convert(map, mapping);
    }

    /**
     * 订单信息转换器
     *
     * @param map     原始值Map类型
     * @param mapping 字段映射关系（项目中这块可以考虑作为动态配置）
     * @return
     */
    public static OrderInfo convert(Map map, Map<String, String> mapping)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        OrderInfo info = new OrderInfo();

        for (String commonKey : mapping.keySet()) {
            String setMethodName = "set" + commonKey.substring(0, 1).toUpperCase() + commonKey.substring(1);
            String selfKey = mapping.get(commonKey); // 通用字段名与自己字段名的映射关系
            OrderInfo.class.getMethod(setMethodName, String.class).invoke(info, map.get(selfKey).toString());
        }
        return info;
    }

}
