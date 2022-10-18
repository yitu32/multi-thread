package com.yitu32.thread.threadlocal;

public class ThreadLocalTest {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new SubThread().start();
        }
    }
    static class SubThread extends Thread {
        @Override
        public void run() {
            String str = Math.random() + "++";
            System.out.println(Thread.currentThread().getName() + " 设置的值为 " + str);
            threadLocal.set(str);
            System.out.println(Thread.currentThread().getName() + " 取出的值为 " + threadLocal.get());
        }
    }
}
