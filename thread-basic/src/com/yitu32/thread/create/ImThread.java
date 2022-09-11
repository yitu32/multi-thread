package com.yitu32.thread.create;

/**
 * 实现Runnable接口
 * 比较三种方式实现的线程的情况
 */
public class ImThread {
    public static void main(String[] args) {
        Thread thread01 = new Thread(new MyRunnable());
        thread01.start();

        for (int i = 1; i <= 10000; i++) {
            System.out.println("mainThread --" + i);
        }
        // 采用匿名内部类的方式
        Thread thread02 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10000; i++) {
                    System.out.println("subThread-02 --" + i);
                }
            }
        });
        thread02.start();

    }
}


class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10000; i++) {
            System.out.println("subThread-01 --" + i);
        }
    }
}
