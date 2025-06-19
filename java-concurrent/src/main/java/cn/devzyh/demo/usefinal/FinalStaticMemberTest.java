package cn.devzyh.demo.usefinal;

/**
 * final修饰的静态变量
 */
public class FinalStaticMemberTest {

    private final static int val;
    // 1.直接初始化
    //private static final int val = 1;

    // 2.静态代码块初始化
    static {
        val = 1;
    }

}
