package com.yitu32.thread.cas;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 测试 AtomicReference
 */
public class CASTest02 {

    static AtomicReference<String> atomicReference = new AtomicReference<>("abc");

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(new Random().nextInt(20));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (atomicReference.compareAndSet("abc", "def")) {
                    System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " 把字符串abc改为def ");
                }
            }).start();
        }

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(new Random().nextInt(20));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (atomicReference.compareAndSet("def", "abc")) {
                    System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " 把字符串def还原为abc ");
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(atomicReference.get());

    }
}
