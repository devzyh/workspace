package cn.devzyh.demo.exam;

import org.junit.Test;

public class ExamPaperTest {

    @Test
    public void createPaper() throws CloneNotSupportedException {
        ExamPaper examPaper = new ExamPaper();
        System.out.println(examPaper.createPaper("张三", "123").toString());
        System.out.println(examPaper.createPaper("李四", "456").toString());
        System.out.println(examPaper.createPaper("王五", "789").toString());
    }
    
}
