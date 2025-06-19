package cn.devzyh.demo.sdk;

/**
 * 三方SDK功能代理实现
 * 方便对原始功能做一些处理工作，比如缓存、增强类的操作
 */
public class ThirdSdkServiceProxy implements IThirdSdkService {

    private IThirdSdkService thirdSdkService;

    public ThirdSdkServiceProxy() {
        thirdSdkService = new ThirdSdkServiceImpl();
    }

    @Override
    public void func() {
        System.out.println("执行原始SDK方法前");
        thirdSdkService.func();
        System.out.println("执行原始SDK方法后");
    }

}
