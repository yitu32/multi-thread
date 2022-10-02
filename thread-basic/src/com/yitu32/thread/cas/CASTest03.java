package com.yitu32.thread.cas;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 测试 AtomicReference的ABA问题
 */
public class CASTest03 {

    static AtomicReference<String> atomicReference = new AtomicReference<>("abc");

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(new Random().nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (atomicReference.compareAndSet("abc", "def")) {
                System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " 把字符串abc改为def ");
            }
        }, "AAA");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(new Random().nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (atomicReference.compareAndSet("def", "abc")) {
                System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " 把字符串def还原为abc ");
            }
        }, "BBB");

        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 这里拿到的abc已经是被改过的abc了
            if (atomicReference.compareAndSet("abc", "ghi")) {
                System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " 把字符串abc更改为ghi ");
            }
        }, "CCC");

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
//        t3.join();
        Thread.sleep(500);
        System.out.println(atomicReference.get());

    }
}
