package cn.devzyh.smallspring.test.aop.bean;

public class TestService implements ITestService {
    @Override
    public void hello() {
        System.out.println("Hello Aop");
    }

    @Override
    public void hello1() {
        try {
            Thread.sleep(1000);
            System.out.println("Hello 1 Second");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
