package cn.devzyh.demo.word;

import org.junit.Test;

public class WordTest {

    @Test
    public void run() {
        // 普通人员
        Word w1 = new Word();
        w1.setAdmin(false);
        System.out.println(w1.getStatus().post());
        System.out.println(w1.getStatus().post());
        System.out.println(w1.getStatus().post());
        System.out.println(w1.getStatus().redo());
        System.out.println(w1.getStatus().redo());

        // 管理人员
        Word w2 = new Word();
        w2.setAdmin(true);
        System.out.println(w2.getStatus().post());
        System.out.println(w2.getStatus().post());
        System.out.println(w2.getStatus().redo());
        System.out.println(w2.getStatus().redo());
    }

}