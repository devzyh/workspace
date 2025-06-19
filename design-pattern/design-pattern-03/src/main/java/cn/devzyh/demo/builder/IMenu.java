package cn.devzyh.demo.builder;

/**
 * 物料清单接口
 */
public interface IMenu {

    /**
     * 添加吊顶
     *
     * @param ceiling
     * @return
     */
    IMenu appendCeiling(IMaterial ceiling);

    /**
     * 添加涂料
     *
     * @param coat
     * @return
     */
    IMenu appendCoat(IMaterial coat);

    /**
     * 添加地板
     *
     * @param floor
     * @return
     */
    IMenu appendFloor(IMaterial floor);

    /**
     * 输出清单
     */
    void output(Double area);
}
