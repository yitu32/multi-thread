package com.yitu32.thread.lock.reentrant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static Lock lock = new ReentrantLock();

    public static void m1(){
        try {
            lock.lock();
            // 业务逻辑
            System.out.println(Thread.currentThread().getName() + "====== method 11111 start ======");
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName() + "====== method 11111 end ======");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void m2(){
        try {
            lock.lock();
            // 业务逻辑
            System.out.println(Thread.currentThread().getName() + "====== method 22222 start ======");
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName() + "====== method 22222 end ======");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void m3(){
        try {
            lock.lock();
            lock.lock();
            System.out.println("======================测试锁的可重入性 start ====================");
            // 业务逻辑
            System.out.println(Thread.currentThread().getName() + "====== method 33333 start ======");
            Thread.sleep(new Random().nextInt(1000));
            m1();
            System.out.println(Thread.currentThread().getName() + "====== method 33333 end ======");
            System.out.println("======================测试锁的可重入性 end ====================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            m1();
        });
        Thread t2 = new Thread(() -> {
            m1();
        });
        Thread t3 = new Thread(() -> {
            m1();
        });
        Thread t4 = new Thread(() -> {
            m2();
        });
        Thread t5 = new Thread(() -> {
            m2();
        });
        Thread t6 = new Thread(() -> {
            m2();
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        new Thread(()-> m3()).start();
    }
}
