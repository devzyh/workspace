package cn.devzyh.demo.word.status;

import cn.devzyh.demo.word.WordStatus;
import cn.devzyh.demo.word.Word;

/**
 * 审核状态
 */
public class Review extends WordStatus {

    public Review(Word word) {
        super(word);
    }

    @Override
    public String post() {
        this.word.changeStatus(new Release(this.word));
        return "转为发布状态";
    }

    @Override
    public String redo() {
        this.word.changeStatus(new Draft(this.word));
        return "转为草稿状态";
    }
}
