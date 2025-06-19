package cn.devzyh.smallspring.context.annotation;

import java.lang.annotation.*;

/**
 * Bean对象注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}