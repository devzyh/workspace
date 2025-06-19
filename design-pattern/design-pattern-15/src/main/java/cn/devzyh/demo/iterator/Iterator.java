package cn.devzyh.demo.iterator;

/**
 * 迭代器接口
 *
 * @param <T>
 */
public interface Iterator<T> {

    /**
     * 是否存在下一个元素
     *
     * @return
     */
    boolean hasNext();

    /**
     * 获取下一个元素
     *
     * @return
     */
    T next();

    /**
     * 获取迭代器对象
     */
    Iterator iter();

}
