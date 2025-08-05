package cn.devzyh.smallspring.context.annotation;

import java.lang.annotation.*;

/**
 * 对象作用域注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}