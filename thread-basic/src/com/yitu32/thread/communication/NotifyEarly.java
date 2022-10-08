package com.yitu32.thread.communication;

/**
 * 为了避免通知过早，可以增加一个判断标志
 */
public class NotifyEarly {

    public static volatile Boolean first = true;
    public static String lock = "lock";

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (first){
                    try {
                        System.out.println("begin wait...");
                        lock.wait();
                        System.out.println("end wait...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("不用等了。。。");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("begin notify...");
                lock.notify();
                System.out.println("end notify...");
                first = false;
            }
        });

        t1.start();
        t2.start();
    }

}
