package com.study.tqw;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author tqw
 */
public class GCLogAnalysis {

    private static SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) {
        // 当前时间ms数
        long startMs = System.currentTimeMillis();
        // 持续运行ms
        long timeoutMs = TimeUnit.SECONDS.toMillis(1);
        // 结束时间戳
        long endMs = startMs + timeoutMs;
        LongAdder counter = new LongAdder();
        System.out.println("正在执行....");
        // 缓存一部分对象进入老年代
        int cacheSize = 2000;
        Object[] cacheGarbage = new Object[cacheSize];
        while (System.currentTimeMillis() < endMs) {
            // 生成垃圾对象
            Object garbage = generateGarbage(100 * 1024);
            counter.increment();
            int randomSize = RANDOM.nextInt(2 * cacheSize);
            if (randomSize < cacheSize) {
                cacheGarbage[randomSize] = garbage;
            }

        }
        System.out.println("执行结束。总共生成对象次数:" + counter);

    }

    private static Object generateGarbage(int max) {
        int randomSize = RANDOM.nextInt(max);
        int type = randomSize % 4;
        Object result = null;
        switch (type) {
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder builder = new StringBuilder();
                String randomString = "randomString-Anything";
                while (builder.length() < randomSize) {
                    builder.append(randomString);
                    builder.append(max);
                    builder.append(randomSize);
                }
                result = builder.toString();
                break;
        }
        return result;
    }
}
