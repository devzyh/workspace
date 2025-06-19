package cn.devzyh.demo.datetime;

import java.time.*;
import java.util.Set;
import java.util.TimeZone;

public class ZoneIdTest {

    public static void main(String[] args) {

        // 获取系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);

        // 通过 getAvailableZoneIds() 方法获取所有合法的“区域/城市”字符串
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.stream().forEach(System.out::println);

        // of() 方法接收一个“区域/城市”的字符串作为参数
        ZoneId of = ZoneId.of("Europe/Athens");
        System.out.println(of);

        // 老的时区类 TimeZone，Java 8也提供了转化方法
        ZoneId id = TimeZone.getDefault().toZoneId();
        System.out.println(id);

        // 有了 ZoneId，我们就可以将一个 LocalDate、LocalTime 或 LocalDateTime 对象转化为 ZonedDateTime 对象
        ZonedDateTime time = ZonedDateTime.of(LocalDateTime.now(), of);
        System.out.println(time);

        // 另一种表示时区的方式是使用 ZoneOffset，它是以当前时间和 世界标准时间（UTC）/格林威治时间（GMT） 的偏差来计算
        ZoneOffset zoneOffset = ZoneOffset.of("+02:00");
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.now(), zoneOffset);
        System.out.println(offsetDateTime);

    }
}
