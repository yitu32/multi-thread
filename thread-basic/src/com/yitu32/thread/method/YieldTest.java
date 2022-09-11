package com.yitu32.thread.method;

/**
 * 测试yield
 */
public class YieldTest {
    public static void main(String[] args) {
        new Thread(() -> {
            int sum = 0;
            long begin = System.currentTimeMillis();
            for (int i = 0; i < 10000000; i++) {
                sum += i;
                Thread.yield();
            }
            long end = System.currentTimeMillis();
            System.out.println("子线程用时：" + (end - begin));
        }).start();

        int sum = 0;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("main线程用时：" + (end - begin));
    }
}

