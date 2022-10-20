package com.yitu32.thread.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyTest {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();

        Thread t1 = new Thread(() -> {
            a.m1();
        }, "t1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(10);
        Thread t2 = new Thread(() -> {
            a.m1();
        }, "t2");
        t2.start();
        System.out.println(Thread.currentThread().getName() + "======interrupt()=======t2");
        t2.interrupt();
    }


    static class A {
        Lock lock = new ReentrantLock();

        void m1() {
            try {
                System.out.println(Thread.currentThread().getName() + "=============准备获取锁================");
//                lock.lock();
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "=============method begin================");
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    new StringBuffer();
                }
                System.out.println(Thread.currentThread().getName() + "=============method end================");
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + "=============出现异常 ================" + e.toString());
            } finally {
                try {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "=============unlock================");
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + "=============没得到锁，释放个啥================");
                }
            }
        }

    }
}
