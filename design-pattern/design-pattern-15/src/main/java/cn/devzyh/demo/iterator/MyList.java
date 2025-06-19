package cn.devzyh.demo.iterator;

import java.util.LinkedList;

/**
 * 我的列表对象
 * 这里借助LinkedList实现List，仅做迭代器实现演示
 *
 * @param <T>
 */
public class MyList<T> extends LinkedList<T> implements Iterator<T> {

    // 记录当前迭代器位置
    private int index = 0;

    @Override
    public boolean hasNext() {
        return this.index < this.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            return null;
        }

        return this.get(this.index++);
    }

    @Override
    public Iterator<T> iter() {
        this.index = 0;
        return this;
    }
}
