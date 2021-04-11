package com.qw.study.method7;

import com.qw.study.ComputeSum;

import java.util.stream.IntStream;

/**
 * 利用java8的方式，并行的流执行可以产生一个feature task的阻塞拿到值。
 *
 * @author tqw
 */
public class Method7 {

    public static void main(String[] args) {
        Integer value =
                IntStream.range(10, 12).parallel().limit(1).mapToObj(
                        ComputeSum::getTotalSum
                ).findFirst().orElse(null);
        System.out.println("当前的值" + value);
    }

}
