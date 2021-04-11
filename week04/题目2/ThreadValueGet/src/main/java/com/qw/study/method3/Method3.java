package com.qw.study.method3;

import com.qw.study.ComputeSum;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 这种方式是采用FutureTask的方式去获取返回值,以get的方式的阻塞的方式。
 * 变成类似于同步获取的方式。
 *
 * @author tqw
 */
public class Method3 {

    public static void main(String[] args) {

        FutureTask<Integer> task = new FutureTask<>(() -> {
            System.out.println("当前子线程执行");
            return ComputeSum.getTotalSum(10);
        });
        task.run();
        try {
            System.out.println("当前计算值:" + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


}
