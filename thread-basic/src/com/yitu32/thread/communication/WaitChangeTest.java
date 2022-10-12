package com.yitu32.thread.communication;

import java.util.ArrayList;
import java.util.List;

public class WaitChangeTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Source.get();
        });
        Thread t2 = new Thread(() -> {
            Source.get();
        });
        Thread t3 = new Thread(() -> {
            Source.add();
        });
        t1.start();
        t2.start();
        t3.start();
        /*
         * 一个生产者，两个消费者，只有一个消费者顺利拿到数据，另一个消费者只能在那里等待
         */
    }

}

class Source {

    static List list = new ArrayList();

    static void get() {
        synchronized (list) {
            // if(list.size() == 0) { // 这里如果是if，线程被唤醒时就不会再判断list.size() 就会往下走
            while (list.size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " begin wait...");
                    list.wait();
                    System.out.println(Thread.currentThread().getName() + " end wait...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object data = list.remove(0);
            System.out.println(Thread.currentThread().getName() + " 从集合中取出了" + data + "，剩余" + list.size());
        }
    }

    static void add() {
        synchronized (list) {
            list.add("data");
            System.out.println(Thread.currentThread().getName() + " 增加了一个数据，集合大小" + list.size());
            list.notifyAll();
        }
    }

}
