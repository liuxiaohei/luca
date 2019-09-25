package org.ld.examples.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateDemo {

    @Test
    public void demo1() {
        LocalDate date = LocalDate.now();
        System.out.println("当前日期=" + date); // 当前日期=2019-09-25
    }

    @Test
    public void demo2() {
        LocalDate date = LocalDate.of(2000, 1, 1);
        System.out.println("千禧年=" + date); // 千禧年=2000-01-01
    }

    @Test
    public void demo3() {
        LocalDate date = LocalDate.now();
        System.out.printf("年=%d， 月=%d， 日=%d", date.getYear(), date.getMonthValue(), date.getDayOfMonth()); // 年=2019， 月=9， 日=25
    }

    @Test
    public void demo4() {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2018, 9, 24);
        System.out.println("日期是否相等=" + now.equals(date)); // false
    }

    @Test
    public void demo5() {
        LocalTime time = LocalTime.now();
        System.out.println("当前时间=" + time); // 当前时间=20:03:18.768
    }

    @Test
    public void demo6() {
        // 时间增量
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(2);
        System.out.println("newTime=" + newTime);

        // 日期增量
        LocalDate date = LocalDate.now();
        LocalDate newDate = date.plus(1, ChronoUnit.WEEKS);
        System.out.println("newDate=" + newDate);

        //newTime=22:04:36.568
        //newDate=2019-10-02
    }

    @Test
    public void demo7() {
        LocalDate now = LocalDate.now();

        LocalDate date1 = LocalDate.of(2000, 1, 1);
        if (now.isAfter(date1)) {
            System.out.println("千禧年已经过去了");
        }

        LocalDate date2 = LocalDate.of(2020, 1, 1);
        if (now.isBefore(date2)) {
            System.out.println("2020年还未到来");
        }
        // 千禧年已经过去了
        // 2020年还未到来
    }

    @Test
    public void demo8() {
        // 上海时间
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime shanghaiZonedDateTime = ZonedDateTime.now(shanghaiZoneId);

        // 东京时间
        ZoneId tokyoZoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyoZonedDateTime = ZonedDateTime.now(tokyoZoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("上海时间: " + shanghaiZonedDateTime.format(formatter));
        System.out.println("东京时间: " + tokyoZonedDateTime.format(formatter));
    }

    @Test
    public void demo9() {
        // 解析日期
        String dateText = "20180924";
        LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("格式化之后的日期=" + date);

        // 格式化日期
        dateText = date.format(DateTimeFormatter.ISO_DATE);
        System.out.println("dateText=" + dateText);
    }

    @Test
    public void demo10() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 日期时间转字符串
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        System.out.println("nowText=" + nowText);

        // 字符串转日期时间
        String datetimeText = "1999-12-31 23:59:59";
        LocalDateTime datetime = LocalDateTime.parse(datetimeText, formatter);
        System.out.println(datetime);
    }

    // 相关类说明
//    Instant         时间戳
//    Duration        持续时间、时间差
//    LocalDate       只包含日期，比如：2018-09-24
//    LocalTime       只包含时间，比如：10:32:10
//    LocalDateTime   包含日期和时间，比如：2018-09-24 10:32:10
//    Peroid          时间段
//    ZoneOffset      时区偏移量，比如：+8:00
//    ZonedDateTime   带时区的日期时间
//    Clock           时钟，可用于获取当前时间戳
//    java.time.format.DateTimeFormatter      时间格式化类
}
