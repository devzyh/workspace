package cn.devzyh.demo.word;

import cn.devzyh.demo.word.status.Draft;

/**
 * 文档对象
 */
public class Word {

    // 文档内容
    private String title;
    private String content;
    private boolean isAdmin;
    // 文档状态
    private WordStatus status;

    public Word() {
        // 文档初始为草稿状态
        this.status = new Draft(this);
        this.isAdmin = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public WordStatus getStatus() {
        return status;
    }

    /**
     * 改变文档状态
     *
     * @param status
     */
    public void changeStatus(WordStatus status) {
        this.status = status;
    }

}
