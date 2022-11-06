package com.yitu32.thread.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest01 {
    public static void main(String[] args) {
        Task01 task01 = new Task01();
        Thread t1 = new Thread(task01);
        Thread t2 = new Thread(task01);
        t1.start();
        t2.start();
    }

}

class Task01 implements Runnable{
    ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            if(lock.tryLock(2, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName() + "->得到锁...");
                TimeUnit.SECONDS.sleep(3);
            } else {
                System.out.println(Thread.currentThread().getName() + "->没有得到锁...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
}