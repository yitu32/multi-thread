package com.yitu32.thread.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 测试 AtomicStampedReference 解决ABA问题
 */
public class CASTest04 {

    static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("abc", 0);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (atomicStampedReference.compareAndSet("abc", "def",
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1)) {
                System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " 把字符串abc改为def "
                        + " 此时stamp为 " + atomicStampedReference.getStamp());
            }
        }, "AAA");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (atomicStampedReference.compareAndSet("def", "abc",
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1)) {
                System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " 把字符串def还原为abc "
                        + " 此时stamp为 " + atomicStampedReference.getStamp());
            }
        }, "BBB");

        Thread t3 = new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
//            stamp = 2;   // 这里改成2就可以成功
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 这里拿到的abc已经是被改过的abc了
            if (atomicStampedReference.compareAndSet("abc", "ghi",
                    stamp, stamp + 1)) {
                System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " 把字符串abc更改为ghi ");
            } else {
                System.out.print(System.nanoTime() + " " + Thread.currentThread().getName() + " 把字符串abc更改为ghi失败 ->");
                System.out.println(" 当前的stamp为 "
                        + atomicStampedReference.getStamp() + " 期望的stamp为 " + stamp);
            }
        }, "CCC");

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        Thread.sleep(500);
        System.out.println(atomicStampedReference.getReference());

    }
}
