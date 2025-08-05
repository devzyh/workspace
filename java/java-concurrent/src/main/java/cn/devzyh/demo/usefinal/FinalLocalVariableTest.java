package cn.devzyh.demo.usefinal;

/**
 * final修饰的局部变量
 */
public class FinalLocalVariableTest {

    public static void main(String[] args) {
        // 1.不初始化
        final int val;

        // 2.直接初始化
        //final int val = 1;

        // 3.先创建后初始化
        val = 1;
    }
}
