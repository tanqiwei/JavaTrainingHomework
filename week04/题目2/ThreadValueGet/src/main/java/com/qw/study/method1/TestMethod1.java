package com.qw.study.method1;

import com.qw.study.ComputeSum;

/**
 * 第一种方式，主线程直接创建一个thread。<br/>
 * 启动线程。<br/>
 * 阻塞主线程一定时间后，通过外部变量或者传入变量引用的方式，取到最后的值。<br/>
 * 所以不论是以lambda创建thread,然后外部变量引用。还是直接创建一个Thread对象或者Runnable对象的方式，里面存着一个接受结果的变量去构建对象。<br/>
 * 都是一样的，所以后续所有样例都是lambda创建。
 *
 * @author tqw
 */

public class TestMethod1 {

    private static Integer value = null;

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println("当前创建的线程开始执行");
            value = ComputeSum.getTotalSum(5);
            System.out.println();
        });

        thread.start();

        try {
            Thread.sleep(3000);
            System.out.println("thread value is : " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
