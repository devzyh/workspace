package cn.devzyh.demo.datetime;

import java.time.Duration;
import java.time.Instant;

public class DurationTest {

    public static void main(String[] args) {
        // 一个Duration对象表示两个Instant间的一段时间
        // 一个Duration实例是不可变的，当创建出对象后就不能改变它的值了。你只能通过Duration的计算方法，来创建出一个新的Durtaion对象。

        // 使用 Duration 类的工厂方法来创建一个 Duration 对象
        Instant now = Instant.now();
        Instant instant = now.plusSeconds(500);
        Duration duration = Duration.between(now, instant);

        // 一个Duration对象里有两个域：纳秒值（小于一秒的部分），秒钟值（一共有几秒），他们的组合表达了时间长度
        int nano = duration.getNano();
        long seconds = duration.getSeconds();
        // Duration不包含毫秒这个属性

        // 可以转换整个时间到其它单位如纳秒、分钟、小时、天
        long l = duration.toDays();
        long seconds1 = duration.getSeconds();

        // Duration类包含一系列的计算方法
        duration.plusSeconds(2);
        duration.minusMinutes(22);

    }
}
