package com.yitu32.thread.method;

public class SleepThreadTest {
    public static void main(String[] args) {
        SThread sThread = new SThread();
        System.out.println(Thread.currentThread().getName() + "==begin==" + System.currentTimeMillis());
        // 子线程中有sleep ，不影响主线程
        sThread.start();
        System.out.println(Thread.currentThread().getName() + "==end==" + System.currentTimeMillis());
    }
}

class SThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "==begin==" + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "==end==" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
