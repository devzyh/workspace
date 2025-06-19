package cn.devzyh.demo.builder;

import org.junit.Test;

public class PackageBuilderTest {

    @Test
    public void package1() {
        // 100平的房子选套餐一
        PackageBuilder.package1(100D);
    }

    @Test
    public void package2() {
        // 87平的房子选套餐二
        PackageBuilder.package2(87D);
    }
}
