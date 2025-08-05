package cn.devzyh.demo.usefinal;

/**
 * 空白final的使用
 */
public class BlankFinalTest {

    private final int val;

    public BlankFinalTest() {
        val = 1; // 给成员常量默认值
    }

    public BlankFinalTest(int val) {
        this.val = val; // 指定成员常量值
    }
}
