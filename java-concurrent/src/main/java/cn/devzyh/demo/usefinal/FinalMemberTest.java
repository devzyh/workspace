package cn.devzyh.demo.usefinal;

/**
 * final修饰的成员变量测试
 * 必须选择一种方式初始化final修饰的成员变量
 */
public class FinalMemberTest {

    private final int val;
    // 1.直接初始化
    //private final int val = 1;

    // 2.构造代码块初始化
    {
        //  val = 1;
    }

    // 3.构造方法初始化
    public FinalMemberTest() {
        this.val = 1;
    }

    public FinalMemberTest(int val) {
        this.val = val;
    }

}
