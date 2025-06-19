package cn.devzyh.demo.optional;

import java.util.Optional;

/**
 * 干掉空指针
 */
public class OptionalTest {

    public static void main(String[] args) {
        // 返回一个空的Optional实例
        Optional<Integer> opt = Optional.empty();

        // 如果值存在并且满足提供的断言， 就返回包含该值的 Optional 对象；否则返回一个空的 Optional 对象
        opt = Optional.of(123); // 将指定值用 Optional 封装之后返回，如果该值为 null，则抛出一个 NullPointerException 异常
        opt = opt.filter((Integer num) -> {
            return true;
        });
        System.out.println(opt.orElse(789));

        opt = opt.filter((Integer num) -> {
            return false;
        });
        System.out.println(opt.orElse(789)); // 如果有值则将其返回，否则返回一个默认值

        // 如果值存在就返回 true，否则返回 false
        System.out.println(opt.isPresent());

        // 如果值存在，就对该值执行提供的 mapping 函数调用
        opt = Optional.of(123);
        opt = opt.map((Integer num) -> {
            return num * num;
        });
        System.out.println(opt.orElse(789));

        // 如果值存在，就对该值执行提供的 mapping 函数调用，返回一个 Optional 类型的值，否则就返 回一个空的 Optional 对象
        opt = Optional.empty();
        opt = opt.map((Integer num) -> {
            return (int) Math.sqrt(num);
        });
        System.out.println(opt.orElse(789));

        // 如果值存在，就执行使用该值的方法调用，否则什么也不做
        opt = Optional.of(123);
        opt.ifPresent((Integer num) -> {
            System.out.println("num = " + num);
        });

        // 将指定值用 Optional 封装之后返回，如果该值为 null，则返回一个空的 Optional 对象
        opt = Optional.ofNullable(null);
        System.out.println(opt.orElse(1));

        // 如果有值则将其返回，否则返回一个由指定的 Supplier 接口生成的值
        Integer result = opt.orElseGet(() -> {
            return 1234567;
        });
        System.out.println(result);

        // 如果有值则将其返回，否则抛出一个由指定的 Supplier 接口生成的异常
        opt.orElseThrow(() -> {
            return new RuntimeException("NPE");
        });

    }
}
