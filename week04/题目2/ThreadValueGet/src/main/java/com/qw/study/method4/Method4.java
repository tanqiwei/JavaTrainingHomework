package com.qw.study.method4;

import com.qw.study.ComputeSum;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 同样也是Future的方式,但是采用构造一个线程去执行
 *
 * @author tqw
 */
public class Method4 {

    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            System.out.println("当前子线程执行");
            return ComputeSum.getTotalSum(12);
        });
        Thread thread = new Thread(task);
        thread.start();
        try {
            System.out.println("当前计算值:" + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
