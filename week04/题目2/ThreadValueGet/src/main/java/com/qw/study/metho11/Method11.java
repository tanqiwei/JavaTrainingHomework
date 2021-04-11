package com.qw.study.metho11;

import com.qw.study.ComputeSum;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 以 不断轮询 的方式阻塞主线程去拿到子线程的结果
 *
 * @author tqw
 */
public class Method11 {


    public static void main(String[] args) {
        AtomicReference<Integer> result = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            System.out.println("当前子线程执行");
            result.set(ComputeSum.getTotalSum(25));
        });
        thread.start();
        while (Objects.isNull(result.get())) {
            System.out.println("等待子线程出结果");
        }
        System.out.println("等待成功:" + result.get());
    }

}
