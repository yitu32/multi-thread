package com.yitu32.thread.communication;

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new SubThread());
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}

class SubThread implements Runnable{
    static Object lock = new Object();
    @Override
    public void run() {
         synchronized (lock){
             try {
                 System.out.println("开始等待。。。");
                 lock.wait();
                 System.out.println("结束等待。。。");
             } catch (InterruptedException e) {
                 System.out.println("线程被中断。。。");
                 e.printStackTrace();
             }
         }
    }
}
