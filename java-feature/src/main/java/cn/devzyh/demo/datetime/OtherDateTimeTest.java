package cn.devzyh.demo.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class OtherDateTimeTest {

    public static void main(String[] args) {

        // Java 8中的日期/时间类都是不可变的，这是为了保证线程安全。当然，新的日期/时间类也提供了方法用于创建对象的可变版本，比如增加一天或者减少一天
        LocalDateTime of = LocalDateTime.of(2008, 5, 12, 1, 2, 3);

        // 修改年份
        LocalDateTime localDateTime = of.withYear(2012);
        // 修改月份
        LocalDateTime localDateTime1 = of.withMonth(8);
        // 修改天
        LocalDateTime localDateTime2 = of.withDayOfMonth(9);

        // 加一年
        LocalDateTime localDateTime3 = of.plusYears(1);
        // 减三个月
        LocalDateTime localDateTime4 = of.minusMonths(3);
        // 加五天
        LocalDateTime localDateTime5 = of.plusDays(5);


        // 有些时候我们要面临更复杂的时间操作，比如将时间调到下一个工作日， 或者是下个月的最后一天，这时候我们可以使用 with() 方法的另一个重载方法
        // 接收一个TemporalAdjuster参数， 可以使我们更加灵活的调整日期

        LocalDate now = LocalDate.now();

        // 返回下一个距离当前时间最近的星期日
        LocalDate nextSaturday = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        System.out.println(nextSaturday);

        // 返回本月最后一个星期六
        LocalDate lastSunday = now.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println(lastSunday);

        // 如果上面表格中列出的方法不能满足你的需求，你还可以创建自定义的 TemporalAdjuster 接口的实现， TemporalAdjuster 也是一个函数式接口
    }
}
