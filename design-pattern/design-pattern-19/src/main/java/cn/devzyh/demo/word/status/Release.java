package cn.devzyh.demo.word.status;

import cn.devzyh.demo.word.WordStatus;
import cn.devzyh.demo.word.Word;

/**
 * 最终状态
 */
public class Release extends WordStatus {

    public Release(Word word) {
        super(word);
    }

    @Override
    public String post() {
        return "最后的状态不能再操作了";
    }

    @Override
    public String redo() {
        this.word.changeStatus(new Draft(this.word));
        return "转为草稿状态";
    }
}
