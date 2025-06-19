package cn.devzyh.demo.exam.domain;

/**
 * 问答题
 */
public class AnswerQuestion {

    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AnswerQuestion() {
    }

    public AnswerQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    
}
