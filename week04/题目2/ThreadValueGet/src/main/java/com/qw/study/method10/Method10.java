package com.qw.study.method10;

import com.qw.study.ComputeSum;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * 和方法8，9也一样，都是用信号协调阻塞的方式
 *
 * @author tqw
 */
public class Method10 {

    public static void main(String[] args) {
        Thread currentMainThread = Thread.currentThread();
        AtomicReference<Integer> result = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            System.out.println("当前子线程执行");
            result.set(ComputeSum.getTotalSum(200));
            LockSupport.unpark(currentMainThread);
        });
        thread.start();

        LockSupport.park();
        System.out.println("当前计算值:" + result);
    }

}
