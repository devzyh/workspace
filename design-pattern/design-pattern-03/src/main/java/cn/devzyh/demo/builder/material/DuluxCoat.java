package cn.devzyh.demo.builder.material;

import cn.devzyh.demo.builder.IMaterial;

public class DuluxCoat implements IMaterial {
    @Override
    public String getType() {
        return "涂料";
    }

    @Override
    public String getBrand() {
        return "多乐士";
    }

    @Override
    public Double getPrice() {
        return 300D;
    }
}
