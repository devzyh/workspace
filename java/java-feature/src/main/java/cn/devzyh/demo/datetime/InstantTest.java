package cn.devzyh.demo.datetime;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;

public class InstantTest {

    public static void main(String[] args) {

        // Instant类在Java日期与时间功能中，表示了时间线上一个确切的点，定义为距离初始时间的时间差（初始时间为GMT 1970年1月1日00:00）

        // 创建一个确切的表达当前时间的Instant对象
        Instant now = Instant.now();

        // 距离初始时间的秒钟数
        // seconds 表示从 1970-01-01 00:00:00 开始到现在的秒数
        System.out.println(now.getEpochSecond());

        // 在当前一秒内的第几纳秒
        // nanos 表示纳秒部分（nanos的值不会超过999,999,999）
        System.out.println(now.getNano());

        // Instant类有一些方法，可以用于获得另一Instant的值
        Instant instant = now.plusSeconds(5000);
        System.out.println(instant.getEpochSecond());

        // Instant 类也可以用来创建老的 java.util.Date 对象
        java.util.Date from = Date.from(instant);
        System.out.println(from);


        // Clock类提供了访问当前日期和时间的方法，Clock是时区敏感的

        // 取代 System.currentTimeMillis() 来获取当前的微秒数。
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);

        // 某一个特定的时间点也可以使用Instant类来表示
        Instant instant1 = clock.instant();
        System.out.println(instant1.getEpochSecond());
    }
}
