package com.yitu32.thread.method;

import java.util.concurrent.TimeUnit;

public class SetDaemonTest {
    public static void main(String[] args) {
        SubThread01 subThread01 = new SubThread01();
        subThread01.setDaemon(true);
        subThread01.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main线程.............");
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("main线程结束......");
    }
}


class SubThread01 extends Thread {
    @Override
    public void run() {
        // 如果当前线程是守护线程，在该线程中创建的线程仍然是守护线程
        SubThread02 subThread02 = new SubThread02();
        System.out.println("SubThread02是不是守护线程："+ subThread02.isDaemon());
        subThread02.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("SubThread01111111111111111......");
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("SubThread01线程结束......");
    }
}

class SubThread02 extends Thread {
    @Override
    public void run() {
        System.out.println("SubThread02是不是守护线程："+Thread.currentThread().isDaemon());
    }
}
