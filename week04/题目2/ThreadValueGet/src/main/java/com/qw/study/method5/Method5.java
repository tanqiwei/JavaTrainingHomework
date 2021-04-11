package com.qw.study.method5;

import com.qw.study.ComputeSum;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 也是以FutureTask拿到返回值。不过是以线程池的方式进行提交。
 *
 * @author tqw
 */
public class Method5 {

    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            System.out.println("当前子线程执行");
            return ComputeSum.getTotalSum(12);
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(task);
        try {
            System.out.println("当前计算值:" + task.get());
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
