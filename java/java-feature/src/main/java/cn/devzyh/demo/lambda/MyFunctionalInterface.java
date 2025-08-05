package cn.devzyh.demo.lambda;

/**
 * 自定义函数式接口
 */
@FunctionalInterface
public interface MyFunctionalInterface<T extends Comparable> {

    /**
     * 提供一个入参的函数式接口方法
     *
     * @param t
     * @return
     */
    T cal(T t);

    /**
     * 提供两个入参的函数式接口方法
     * 因为函数式接口只允许一个抽象方法，所以这个只能使用默认方法了
     *
     * @param t1
     * @param t2
     * @return
     */
    default int compare(T t1, T t2) {
        return t1.compareTo(t2);
    }

}
