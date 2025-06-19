package cn.devzyh.demo.proxy.manual.inherit;

/**
 * 学生服务类的代理对象
 * 这里做一些增强操作
 */
public class StudentProxyService extends StudentService {

    @Override
    public void eat() {
        System.out.println("吃饭前先排队打饭");

        // 调用被代理对象方法
        super.eat();

        System.out.println("吃饭后去洗碗");
    }

    /**
     * 静态代理继承方式实现的测试类
     *
     * @param args
     */
    public static void main(String[] args) {
        StudentProxyService proxyService = new StudentProxyService();
        proxyService.eat();
    }

}
