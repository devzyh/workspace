package cn.devzyh.demo.sdk;

import org.junit.Test;

public class IThirdSdkServiceTest {

    @Test
    public void func() {
        IThirdSdkService s1 = new ThirdSdkServiceImpl();
        IThirdSdkService s2 = new ThirdSdkServiceProxy();
        s1.func();
        s2.func();
    }
}