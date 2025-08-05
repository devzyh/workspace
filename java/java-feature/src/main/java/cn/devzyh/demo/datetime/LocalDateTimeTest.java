package cn.devzyh.demo.datetime;

import java.time.*;

public class LocalDateTimeTest {

    public static void main(String[] args) {
        testZonedDateTime();
    }

    static void testLocalDate() {
        // LocalDate类是Java 8中日期时间功能里表示一个本地日期的类，它的日期是无时区属性的
        // 创建出来的实例也是不可变对象，所以涉及它的计算方法将返回一个新的LocalDate

        // 获得值为今天当日的 LocalDate 对象
        LocalDate now = LocalDate.now();

        // 使用年月日信息构造出LocalDate对象
        LocalDate of = LocalDate.of(2022, 1, 2);

        // 访问LocalDate中的日期信息
        int year = now.getYear();
        Month month = now.getMonth();
        int dayOfMonth = now.getDayOfMonth();

        // 可以进行一堆简单的日期计算
        now.plusYears(1);
        now.minusDays(1);
        now.plusMonths(2);
    }

    static void testLocalTime() {
        // LocalTime类是Java 8中日期时间功能里表示一整天中某个时间点的类，它的时间是无时区属性的
        //  LocalTime类的对象也是不可变的，所以计算方法会返回一个新的LocalTime实例

        // 使用当前时间作为值新建对象
        LocalTime now = LocalTime.now();

        // 使用指定的时分秒和纳秒来新建对象
        LocalTime of = LocalTime.of(23, 12, 1, 22000);

        // 访问LocalTime对象的时间
        now.getHour();
        now.getMinute();

        // LocalTime的计算
        now.plusHours(1);
    }

    static void testLocalDateTime() {
        // LocalDateTime类是Java 8中日期时间功能里，用于表示当地的日期与时间的类，它的值是无时区属性的。
        // LocalDateTime类的值是不可变的，所以其计算方法会返回一个新的LocalDateTime实例

        // 通过LocalDateTime的静态工厂方法来创建LocalDateTime实例
        LocalDateTime now = LocalDateTime.now();

        // 使用指定的年月日、时分秒、纳秒来新建对象
        LocalDateTime of = LocalDateTime.of(2012, 1, 2, 3, 4, 5, 6);

        // 访问LocalDateTime对象的时间
        now.getDayOfMonth();
        now.getNano();

        // LocalDateTime的计算
        now.plusSeconds(1);
        now.minusHours(2);
    }

    static void testZonedDateTime() {
        // ZonedDateTime类是Java 8中日期时间功能里，用于表示带时区的日期与时间信息的类。
        // ZonedDateTime 类的值是不可变的，所以其计算方法会返回一个新的ZonedDateTime 实例。

        // 使用当前时间作为值新建对象
        ZonedDateTime now = ZonedDateTime.now();

        // 使用指定的年月日、时分秒、纳秒以及时区ID来新建对象
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime of = ZonedDateTime.of(2022, 1, 2, 3, 4, 5, 6, zoneId);

        // 访问ZonedDateTime对象的时间
        ZoneId zone = now.getZone();
        int year = now.getYear();
        int second = now.getSecond();

        // ZonedDateTime的计算
        now.plusHours(2);
        now.minusDays(1);

        // 注意计算时，若不巧跨越了夏令时（会补一小时或减一小时），可能得不到希望的结果 一个替代的正确做法是使用Period
        ZonedDateTime zonedDateTime = now.plus(Period.ofDays(2));
    }
}
