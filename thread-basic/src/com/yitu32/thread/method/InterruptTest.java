package com.yitu32.thread.method;

public class InterruptTest {
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("线程即将中断");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "==> " + i);
            }
        });
        a.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"==> "+i);
        }
        // 给一个中断标志
        a.interrupt();
    }

}
