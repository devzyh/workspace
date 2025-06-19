package cn.devzyh.demo.builder;

/**
 * 物料接口
 */
public interface IMaterial {

    /**
     * 获取物料类型
     *
     * @return
     */
    String getType();

    /**
     * 获取物料品牌
     *
     * @return
     */
    String getBrand();

    /**
     * 获取物料单价
     *
     * @return
     */
    Double getPrice();
    
}
