package cn.devzyh.demo.builder;

import cn.devzyh.demo.builder.material.*;

/**
 * 装修套餐建造者
 */
public class PackageBuilder {

    /**
     * 套餐一
     *
     * @param area
     */
    public static void package1(Double area) {
        IMenu menu = new PackageMenu();
        menu.appendCeiling(new LevelTwoCeiling())
                .appendCoat(new LiBangCoat())
                .appendFloor(new ShengXiangFloor());
        menu.output(area);
    }


    /**
     * 套餐二
     *
     * @param area
     */
    public static void package2(Double area) {
        IMenu menu = new PackageMenu();
        menu.appendCeiling(new LevelTwoCeiling())
                .appendCoat(new DuluxCoat())
                .appendFloor(new MarcoPoloFloor());
        menu.output(area);
    }

}
