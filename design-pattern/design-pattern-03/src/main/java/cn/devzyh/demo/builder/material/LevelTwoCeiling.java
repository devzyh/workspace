package cn.devzyh.demo.builder.material;

import cn.devzyh.demo.builder.IMaterial;

public class LevelTwoCeiling implements IMaterial {
    
    @Override
    public String getType() {
        return "吊顶";
    }

    @Override
    public String getBrand() {
        return "二级顶";
    }

    @Override
    public Double getPrice() {
        return 500D;
    }
    
}
