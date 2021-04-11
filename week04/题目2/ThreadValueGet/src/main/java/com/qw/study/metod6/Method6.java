package com.qw.study.metod6;

import com.qw.study.ComputeSum;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 利用CompletableFuture的特性提交
 *
 * @author tqw
 */
public class Method6 {

    public static void main(String[] args) {
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前任务执行");
            return ComputeSum.getTotalSum(14);
        });
        try {
            System.out.println("当前返回值" + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
