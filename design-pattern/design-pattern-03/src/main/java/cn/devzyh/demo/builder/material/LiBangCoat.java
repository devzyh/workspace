package cn.devzyh.demo.builder.material;

import cn.devzyh.demo.builder.IMaterial;

public class LiBangCoat implements IMaterial {

    @Override
    public String getType() {
        return "涂料";
    }

    @Override
    public String getBrand() {
        return "立邦";
    }

    @Override
    public Double getPrice() {
        return 888D;
    }
}
