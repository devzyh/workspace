package cn.devzyh.smallspring.test.annotation.bean;

import cn.devzyh.smallspring.context.annotation.Component;

import java.util.HashMap;
import java.util.Map;

@Component("testMapper")
public class TestMapper {

    private static final Map<String, String> data = new HashMap();

    static {
        data.put("zs", "张三");
    }

    String queryName(String key) {
        return data.get(key);
    }
}
