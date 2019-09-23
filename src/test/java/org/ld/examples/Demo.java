package org.ld.examples;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.stream.IntStream;

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
                        .divide(new BigDecimal(Math.pow(2,i)))
                )
                .mapToDouble(BigDecimal::doubleValue).sum();
    }

    @Test
    public void demo() {
        IntStream.rangeClosed(1, 24).boxed().forEach(i -> System.out.println("维度:" + i + "\t" + "优化版:" + (double)Math.round(totalTime(i) * 100)/100 + "\t" + "去优化版:" + Math.round(Math.pow(2, i))));
    }
}
