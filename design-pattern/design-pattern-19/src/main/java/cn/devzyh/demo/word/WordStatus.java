package cn.devzyh.demo.word;

/**
 * 文档状态抽象类
 * 基本状态流程：
 * 管理员： 草稿->最终
 * 普通用户： 草稿->审核->最终
 */
public abstract class WordStatus {

    // 文档对象
    protected Word word;

    public WordStatus(Word word) {
        this.word = word;
    }

    /**
     * 发布文档
     *
     * @return
     */
    public abstract String post();

    /**
     * 重做文档
     *
     * @return
     */
    public abstract String redo();

}
