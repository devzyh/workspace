package cn.devzyh.demo.word.status;

import cn.devzyh.demo.word.WordStatus;
import cn.devzyh.demo.word.Word;

/**
 * 草稿状态
 */
public class Draft extends WordStatus {

    public Draft(Word word) {
        super(word);
    }

    @Override
    public String post() {
        // 普通人员需要审核
        if (this.word.isAdmin()) {
            this.word.changeStatus(new Release(this.word));
            return "转为发布状态";
        } else {
            this.word.changeStatus(new Review(this.word));
            return "转为审核状态";

        }
    }

    @Override
    public String redo() {
        return "最开始状态不能再操作了";
    }
}
