package com.yitu32.thread.create;

/**
 * 测试线程执行的随机性
 */
public class RandomThread {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("mainThread--" + i);
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("subThread--" + i);
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
