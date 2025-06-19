package cn.devzyh.demo.factory;

/**
 * Redis操作适配器接口
 * 这个接口从改造前的Redis工具类提取过来，并让改造前工具类实现这个接口。
 */
public interface IRedisAdapter {

    Object get(String key);
    
    void set(String key, Object val);

    void del(String key);
    
}
