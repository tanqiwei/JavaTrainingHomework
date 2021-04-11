package com.qw.study.method2;

import com.qw.study.ComputeSum;

/**
 * 和第一种方式也是主线程创建一个线程,返回值用外部另一个变量变量接收。<br/>
 * 此时用join方式把主线程阻塞以等待子线程执行完毕
 *
 * @author tqw
 */
public class Method2 {

    public static Integer value;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("当前创建的线程开始执行");
            value = ComputeSum.getTotalSum(5);
            System.out.println();
        });
        thread.start();
        try {
            thread.join();
            System.out.println("当前值:" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
