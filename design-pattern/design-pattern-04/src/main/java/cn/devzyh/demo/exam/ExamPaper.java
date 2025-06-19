package cn.devzyh.demo.exam;

import cn.devzyh.demo.exam.domain.AnswerQuestion;
import cn.devzyh.demo.exam.domain.ChoiceQuestion;

import java.util.Arrays;

/**
 * 试卷创建
 */
public class ExamPaper {

    private QuestionBank questionBank;

    public ExamPaper() {
        questionBank = new QuestionBank();

        //  添加选择题
        ChoiceQuestion q1 = new ChoiceQuestion();
        q1.setQuestion("下面哪个是基本数据类型？");
        q1.setOptions(Arrays.asList("Object", "Integer", "Exception", "int"));
        q1.setAnswerIndex(3);

        ChoiceQuestion q2 = new ChoiceQuestion();
        q2.setQuestion("下面哪个流类属于面向字符的输入流？");
        q2.setOptions(Arrays.asList("InputStreamReader", "BufferedWriter", "FileInputStream", "ObjectInputStream"));
        q2.setAnswerIndex(0);

        ChoiceQuestion q3 = new ChoiceQuestion();
        q3.setQuestion("下列语句哪一个正确？");
        q3.setOptions(Arrays.asList("Java程序经编译后会产生machine code", "Java程序经编译后会产生byte code",
                "Java程序经编译后会产生DLL", "以上都不正确 "));
        q3.setAnswerIndex(1);

        questionBank.appendChoiceQuestion(q1).appendChoiceQuestion(q2).appendChoiceQuestion(q3);

        // 添加问答题
        questionBank.appendAnswerQuestion(new AnswerQuestion("什么是Java",
                        "Java是一门面向对象编程语言"))
                .appendAnswerQuestion(new AnswerQuestion(" 什么是字节码？",
                        "Java源代码经过虚拟机编译器编译后产生的文件（即扩展为.class的文件）"))
                .appendAnswerQuestion(new AnswerQuestion("什么是跨平台性？",
                        "所谓跨平台性，是指java语言编写的程序，一次编译后，可以在多个系统平台上运行。"));
    }

    /**
     * 创建学生试卷
     *
     * @param name
     * @param examNo
     * @return
     * @throws CloneNotSupportedException
     */
    QuestionBank createPaper(String name, String examNo) throws CloneNotSupportedException {
        this.questionBank.setStudentName(name);
        this.questionBank.setStudentExamNo(examNo);
        return this.questionBank.clone();
    }
}
