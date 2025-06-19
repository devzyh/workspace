package cn.devzyh.demo.usefinal;

/**
 * final修饰的方法
 */
public class FinalMethodTest {

    /**
     * final修饰的方法不能被重写
     */
    final void method() {
        System.out.println("父类");
    }

    static final class SubClass extends FinalMethodTest {
        // 下面内容编译报错
//        final void method() {
//            System.out.println("重写");
//        }
    }

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.method();
    }
}
