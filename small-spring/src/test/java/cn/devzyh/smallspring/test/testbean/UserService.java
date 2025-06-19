package cn.devzyh.smallspring.test.testbean;

import cn.devzyh.smallspring.beans.factory.DisposableBean;
import cn.devzyh.smallspring.beans.factory.InitializingBean;

/**
 * 用户服务层
 */
public class UserService implements InitializingBean, DisposableBean {

    // 依赖Bean对象
    private UserDao userDao;

    // 属性：姓名
    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("Hello Mini Spring");
        System.out.println("Current HashCode = " + this.hashCode());
    }

    public void say() {
        System.out.println("我的名字叫：" + this.name);
    }

    public void printAddress() {
        System.out.printf("%s的住址是：%s\n", this.name, this.userDao.getAddressByName(this.name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("userService对象已销毁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("userService对象属性已填充完毕");
    }
}
