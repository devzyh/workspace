package cn.devzyh.demo.exam.domain;

import java.util.Collections;
import java.util.List;

/**
 * 选择题
 */
public class ChoiceQuestion {

    /**
     * 问题
     */
    private String question;

    /**
     * 选项
     */
    private List<String> options;

    /**
     * 答案选项
     */
    private Integer answerIndex;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Integer getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(Integer answerIndex) {
        this.answerIndex = answerIndex;
    }

    /**
     * 选项乱序处理
     *
     * @return
     */
    public ChoiceQuestion shuffleOptions() {
        String answer = this.options.get(this.answerIndex);
        Collections.shuffle(this.options);
        for (int i = 0; i < this.options.size(); i++) {
            if (answer.equals(this.options.get(i))) {
                this.answerIndex = i;
                break;
            }
        }
        return this;
    }
    
}
