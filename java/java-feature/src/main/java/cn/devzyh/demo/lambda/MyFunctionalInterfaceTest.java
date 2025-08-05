package cn.devzyh.demo.lambda;


public class MyFunctionalInterfaceTest<T extends Comparable> {

    public static void main(String[] args) {
        MyFunctionalInterface<Integer> mf1 = (Integer num) -> {
            return num + num;
        };
        showResult(mf1);

        MyFunctionalInterface<Integer> mf2 = (Integer num) -> {
            return num * num;
        };
        showResult(mf2);
    }

    /**
     * 传入不同的处理函数，返回不同的结果
     *
     * @param f
     */
    static void showResult(MyFunctionalInterface<Integer> f) {
        System.out.println("我调用了一个方法，传入不同的方法返回结果不同");
        System.out.println(f.cal(100));
    }

}
