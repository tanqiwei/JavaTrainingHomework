package com.qw.study.method9;

import com.qw.study.ComputeSum;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 和方法8一样,可以尝试采用另一种信号量机制CyclicBarrier
 *
 * @author tqw
 */
public class Method9 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    private static Integer result;

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                System.out.println("当前子线程在执行");
                result = ComputeSum.getTotalSum(20);
                cyclicBarrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("拿到值" + result);
    }


}
