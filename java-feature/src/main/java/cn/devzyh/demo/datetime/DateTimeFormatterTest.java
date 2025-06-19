package cn.devzyh.demo.datetime;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateTimeFormatterTest {

    public static void main(String[] args) {
        // DateTimeFormatter类是Java 8中日期时间功能里，用于解析和格式化日期时间的类，位于 java.time.format 包下。
        // DateTimeFormatter类包含一系列预定义（常量）的实例，可以解析和格式化一些标准时间格式
        // 每个预定义的DateTimeFormatter实例都有不同的日期格式

        // 获取一个DateTimeFormatter实例后，就可以用format()方便来将一个日期格式化为某种字符串
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = formatter.format(LocalDateTime.now());
        System.out.println(str);

        // 使用JDK自带的实例格式化时间
        str = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now());
        System.out.println(str);

        // 解析时间字符串
        TemporalAccessor parse = formatter.parse("2022-12-12 00:23:56");

    }
}
