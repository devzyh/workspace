package cn.devzyh.demo.usefinal;

/**
 * final和可变性的测试
 */
public class FinalAndChangeTest {

    /**
     * final只能保证基本类型不被改变。
     * 也能保证引用类型的对象地址不被改变，
     * 但是不能保证引用对象里的内容不被改变。
     *
     * @param args
     */
    public static void main(String[] args) {
        final SubClass subClass = new SubClass();
        System.out.println("num = " + subClass.num);
        // subClass = new SubClass(); // 此句编译报错，无法改变常量对象
        subClass.num = 123; // 此句不会报错
        System.out.println("num = " + subClass.num);
    }


    /**
     * 引用对象
     */
    static class SubClass {
        private int num;
    }
}
