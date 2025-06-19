package cn.devzyh.demo.config;

import java.util.LinkedList;
import java.util.List;

/**
 * 配置信息备忘管理员
 * 实现对备忘录历史的管理
 */
public class ConfigInfoMementoAdmin {

    private List<ConfigInfoMemento> mementos = new LinkedList<>();
    private int cursor = -1;

    /**
     * 新增快照 +
     *
     * @param memento
     */
    public void append(ConfigInfoMemento memento) {
        mementos.add(memento);
        cursor++;
    }

    /**
     * 重做  +
     *
     * @return
     */
    public ConfigInfoMemento redo() {
        if (cursor >= mementos.size()) {
            cursor = mementos.size() - 1;
        }
        cursor++;

        return mementos.get(cursor);
    }

    /**
     * 撤销  -
     *
     * @return
     */
    public ConfigInfoMemento undo() {
        if (cursor <= 1) {
            cursor = 1;
        }
        cursor--;

        return mementos.get(cursor);
    }

    /**
     * 获取指定版本快照
     *
     * @param index
     * @return
     */
    public ConfigInfoMemento get(int index) {
        return mementos.get(index);
    }
}
