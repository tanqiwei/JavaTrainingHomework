package com.qw.study.method12;

import com.qw.study.ComputeSum;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 采用生产者消费者模式，让主线程等待子线程唤醒
 *
 * @author tqw
 */
public class method12 {
    private static final AtomicReference<Integer> result = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        Thread subThread = new Thread(() -> {
            synchronized (result) {
                System.out.println("当前子线程在执行");
                result.set(ComputeSum.getTotalSum(219));
                result.notify();
            }

        });
        subThread.start();
        System.out.println("当前值结果" + getSum());
    }

    private static int getSum() {
        synchronized (result) {
            try {
                result.wait();
                return result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }

        }
    }
}
