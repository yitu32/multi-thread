package com.yitu32.thread.atomic;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {
    // 总数
    static AtomicLong sumNum = new AtomicLong(0);
    // 成功数
    static AtomicLong successNum = new AtomicLong(0);
    // 失败树
    static AtomicLong failNum = new AtomicLong(0);

    public static void main(String[] args) {
        
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                sumNum.incrementAndGet();
                int random = new Random().nextInt();
                if (random % 2 == 0) {
                    successNum.incrementAndGet();
                } else {
                    failNum.incrementAndGet();
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("总数="+sumNum.get());
        System.out.println("成功="+successNum.get());
        System.out.println("失败="+failNum.get());
    }


}
