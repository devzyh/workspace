package cn.devzyh.smallspring.test.testbean;

import java.util.HashMap;

/**
 * 用户持久层
 */
public class UserDao {

    // 属性：姓名
    private static HashMap<String, String> addresses = new HashMap<>();

    static {
        addresses.put("张三", "湖北");
        addresses.put("李四", "上海");
        addresses.put("王五", "广州");
    }

    String getAddressByName(String name) {
        return addresses.get(name);
    }

    public void initDataMethod() {
        System.out.println("执行了initDataMethod方法");
    }

    public void destroyDataMethod() {
        System.out.println("执行了destroyDataMethod方法");
    }
}
