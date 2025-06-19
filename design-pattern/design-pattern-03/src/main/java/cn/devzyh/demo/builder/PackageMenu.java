package cn.devzyh.demo.builder;

import java.util.LinkedList;
import java.util.List;

/**
 * 装修套餐清单
 */
public class PackageMenu implements IMenu {

    /**
     * 物料清单
     */
    List<IMaterial> materialList = new LinkedList<>();

    @Override
    public IMenu appendCeiling(IMaterial ceiling) {
        materialList.add(ceiling);
        return this;
    }

    @Override
    public IMenu appendCoat(IMaterial coat) {
        materialList.add(coat);
        return this;
    }

    @Override
    public IMenu appendFloor(IMaterial floor) {
        materialList.add(floor);
        return this;
    }

    @Override
    public void output(Double area) {
        Double sum = 0D;
        System.out.println("套餐清单如下：");
        for (IMaterial material : materialList) {
            sum += (material.getPrice() * area);
            System.out.format("每平米单价%s元的%s%s\n", material.getPrice(), material.getBrand(), material.getType());
        }
        System.out.println("套餐总价：" + sum + "元");
    }
}
