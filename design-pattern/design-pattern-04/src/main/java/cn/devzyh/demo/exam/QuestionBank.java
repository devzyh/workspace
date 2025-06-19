package cn.devzyh.demo.exam;

import cn.devzyh.demo.exam.domain.AnswerQuestion;
import cn.devzyh.demo.exam.domain.ChoiceQuestion;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 试卷问题库
 */
public class QuestionBank implements Cloneable {

    /**
     * 选择题库
     */
    private ArrayList<ChoiceQuestion> choiceQuestionList = new ArrayList<>();

    /**
     * 问答题库
     */
    private ArrayList<AnswerQuestion> answerQuestionList = new ArrayList<>();

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 准考证号
     */
    public String studentExamNo;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentExamNo() {
        return studentExamNo;
    }

    public void setStudentExamNo(String studentExamNo) {
        this.studentExamNo = studentExamNo;
    }

    /**
     * 添加选择题
     *
     * @param question
     * @return
     */
    public QuestionBank appendChoiceQuestion(ChoiceQuestion question) {
        choiceQuestionList.add(question);
        return this;
    }

    /**
     * 添加问答题
     *
     * @param question
     * @return
     */
    public QuestionBank appendAnswerQuestion(AnswerQuestion question) {
        answerQuestionList.add(question);
        return this;
    }

    /**
     * 复制试卷问题，并打乱题目顺序
     *
     * @return
     */
    @Override
    public QuestionBank clone() throws CloneNotSupportedException {
        // 复制原始对象
        QuestionBank qb = (QuestionBank) super.clone();
        qb.choiceQuestionList = (ArrayList<ChoiceQuestion>) this.choiceQuestionList.clone();
        qb.answerQuestionList = (ArrayList<AnswerQuestion>) this.answerQuestionList.clone();

        // 选择题乱序
        Collections.shuffle(qb.choiceQuestionList);

        // 选择题选项乱序
        for (ChoiceQuestion question : qb.choiceQuestionList) {
            question = question.shuffleOptions();
        }

        // 问答题乱序
        Collections.shuffle(qb.answerQuestionList);

        return qb;
    }

    /**
     * 输出试卷信息
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("=====================================");
        builder.append("\n姓名：");
        builder.append(this.studentName);
        builder.append("\n准考证：");
        builder.append(this.studentExamNo);
        builder.append("\n\n一、选择题");
        for (int i = 0; i < this.choiceQuestionList.size(); i++) {
            builder.append("\n第");
            builder.append(i + 1);
            builder.append("题：");
            builder.append(this.choiceQuestionList.get(i).getQuestion());
            // 选项
            for (int j = 0; j < this.choiceQuestionList.get(i).getOptions().size(); j++) {
                builder.append("\n");
                builder.append((char) (65 + j));
                builder.append("：");
                builder.append(this.choiceQuestionList.get(i).getOptions().get(j));
            }
            builder.append("\n答案：");
            builder.append((char) (65 + this.choiceQuestionList.get(i).getAnswerIndex()));
            builder.append("\n");
        }

        builder.append("\n二、问答题");
        for (int i = 0; i < this.answerQuestionList.size(); i++) {
            builder.append("\n第");
            builder.append(i + 1);
            builder.append("题：");
            builder.append(this.answerQuestionList.get(i).getQuestion());
            builder.append("\n答：");
            builder.append(this.answerQuestionList.get(i).getAnswer());
            builder.append("\n");
        }
        builder.append("=====================================");

        return builder.toString();
    }

}
