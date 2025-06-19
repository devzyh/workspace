package cn.devzyh.demo.builder.material;

import cn.devzyh.demo.builder.IMaterial;

public class ShengXiangFloor implements IMaterial {
    @Override
    public String getType() {
        return "地板";
    }

    @Override
    public String getBrand() {
        return "马可波罗";
    }

    @Override
    public Double getPrice() {
        return 666D;
    }
}
