package com.qw.study;

import java.util.stream.IntStream;

/**
 * 这个类进行各线程调用
 *
 * @author tqw
 */
public class ComputeSum {


    public static int getTotalSum(int sum) {
        if (sum > 0) {
            return IntStream.range(0, sum).sum();
        }
        return 0;

    }


}
