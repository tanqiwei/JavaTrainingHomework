package com.qw.study.method8;

import com.qw.study.ComputeSum;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 把主线程看成一种锁，自然能想到利用信号量机制的策略
 *
 * @author tqw
 */
public class Method8 {


    public static void main(String[] args) {
        AtomicReference<Integer> result = new AtomicReference<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            System.out.println("当前子线程在执行");
            result.set(ComputeSum.getTotalSum(18));
            countDownLatch.countDown();
        });
        thread.start();
        try {
            countDownLatch.await();
            System.out.println("当前值:" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
