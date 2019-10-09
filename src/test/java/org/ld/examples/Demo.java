package org.ld.examples;

import org.junit.Test;
import org.ld.classloader.DemoInterface;
import org.ld.exception.CodeStackException;
import org.ld.utils.Logger;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.util.Properties;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 排列组合样例
 */
public class Demo {
    // 求排列数 A(n,m) n>m
    public static int A(int n, int m) {
        int result = 1;
        // 循环m次,如A(6,2)需要循环2次，6*5
        for (int i = m; i > 0; i--) {
            result *= n;
            n--;// 下一次减一
        }
        return result;
    }

    public static int C(int n, int m)// 应用组合数的互补率简化计算量
    {
        int helf = n / 2;
        if (m > helf) {
            //System.out.print(m + "---->");
            m = n - m;
            //System.out.print(m + "\n");
        }
        // 分子的排列数
        int numerator = A(n, m);
        // 分母的排列数
        int denominator = A(m, m);
        return numerator / denominator;
    }

    private double totalTime(int n) {
        return IntStream.rangeClosed(0, n).boxed()
                .map(i -> new BigDecimal(C(n, i))
                        .divide(new BigDecimal(Math.pow(2, i)))
                )
                .mapToDouble(BigDecimal::doubleValue).sum();
    }

    @Test
    public void demo() {
        IntStream.rangeClosed(1, 24).boxed().forEach(i -> System.out.println("维度:" + i + "\t" + "优化版:" + (double) Math.round(totalTime(i) * 100) / 100 + "\t" + "去优化版:" + Math.round(Math.pow(2, i))));
    }

    @Test
    public void urlClassLoader() throws Exception {
        while (true) {
            File filpath = new File("");
            URL url = new URL("file:" + "/tmp");
            //URL url = new URL("http://node2602:50070/webhdfs/v1/tmp/ld/LoadDemo.class?op=OPEN&offset=0&buffersize=10485760&guardian_access_token=8BMmYlZADdotfCD2PoYH-TDH");
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            Class<?> methtClass = classLoader.loadClass("org.ld.examples.nio.LoadDemo");
            Object obj = methtClass.newInstance();
            methtClass.getDeclaredMethod("name").invoke(obj);
            System.out.println(classLoader);
            Thread.sleep(3000);
        }
    }

    @Test
    public void demo1() throws Exception {
        String url = "jdbc:kundb://vt_app:123@172.26.5.56:15991/sharded_ks?useSSL=true&trustAllCA=true";
        String user = "vt_app";
        String password = "123";
        String driverClass = "io.transwarp.kundb.jdbc.KundbDriver";
        String driverJar = "http://node2602:50070/webhdfs/v1/tmp/ld/kundb-jdbc-1.1.0-SNAPSHOT-fatjar.jar?op=OPEN&offset=0&buffersize=10485760&guardian_access_token=8BMmYlZADdotfCD2PoYH-TDH";

        URL urls[] = new URL[]{new URL(driverJar)};
        URLClassLoader loader = new URLClassLoader(urls);
        Class<?> clazz = loader.loadClass(driverClass);
        Driver driver = (Driver) clazz.newInstance();

        Properties p = new Properties();
        p.put("user", user);
        p.put("password", password);

        Connection con = driver.connect(url, p);
        DatabaseMetaData meta = con.getMetaData();
        System.out.println("getDatabaseProductName()=" + meta.getDatabaseProductName());
        System.out.println("getDatabaseProductVersion()=" + meta.getDatabaseProductVersion());
        System.out.println("getDefaultTransactionIsolation()=" + meta.getDefaultTransactionIsolation());
        System.out.println("getDriverName()=" + meta.getDriverName());
        con.close();
    }

    Logger logger = Logger.newInstance();

    @Test
    public void demo2() throws Exception {
        String driverClass = "org.ld.classloader.DemoBean";
        String driverJar = "http://node2602:50070/webhdfs/v1/tmp/ld/lddemo-studio-1.2.0.jar?op=OPEN&offset=0&buffersize=10485760&guardian_access_token=8BMmYlZADdotfCD2PoYH-TDH";
        URL urls[] = new URL[]{new URL(driverJar)};
        URLClassLoader loader = new URLClassLoader(urls);
        Class<?> clazz = loader.loadClass(driverClass);
        Stream.iterate(0, i -> ++i)
                .limit(1)
                .forEach(
                i -> {
                    try {

                        DemoInterface demo = (DemoInterface) clazz.newInstance();
                        demo.set("abc");
                        demo.say();
                        System.out.println(demo.get());
                        System.out.println(demo.name());


                        //driverJar = "http://node2602:50070/webhdfs/v1/tmp/ld/lddemo-studio-1.2.1.jar?op=OPEN&offset=0&buffersize=10485760&guardian_access_token=8BMmYlZADdotfCD2PoYH-TDH";
                        //urls = new URL[]{new URL(driverJar)};
                        //loader = new URLClassLoader(urls);
                        demo = (DemoInterface) ClassLoader.getSystemClassLoader().loadClass(driverClass).newInstance();
                        demo.set("def");
                        demo.say();
                        System.out.println(demo.get());
                        System.out.println(demo.name());
                        logger.debug(() -> "TotalMemory" + i + Runtime.getRuntime().totalMemory() / (1024 * 1024) + "M");
                        logger.debug(() -> "FreeMemory" + i + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "M");
                        logger.debug(() -> "MaxMemory" + i + Runtime.getRuntime().maxMemory() / (1024 * 1024) + "M");
                        logger.debug(() -> "UsedMemory" + i + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + "M");
                    } catch (Exception e1) {
                        throw new CodeStackException(e1);
                    }

                });
    }

    private String aaa (Integer a) {
        try {
             return (100/a) + "";
        } catch (Exception e) {
            throw new CodeStackException(e);
        }
    }

    private String bbb (Integer a) {
        try {
            return aaa(a);
        } catch (Exception e) {
            throw new CodeStackException(e);
        }
    }

    @Test
    public void demo3() {
        try {
            System.out.println(bbb(3));
            System.out.println(bbb(2));
            System.out.println(bbb(1));
            System.out.println(bbb(0));
        } catch (Exception e) {
            logger.printStackTrace(e);
        }
    }
}
