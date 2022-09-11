package com.yitu32.thread.method;

public class IsAliveTest {
    public static void main(String[] args) throws InterruptedException {
        IThread iThread = new IThread();
        System.out.println("begin ==> "+iThread.isAlive());
        iThread.start();
        Thread.sleep(1);
        System.out.println("end ==> "+iThread.isAlive());  // 不确定，此时iThread线程可能还没结束
    }
}

class IThread extends Thread{
    @Override
    public void run() {
        System.out.println("run方法中，this.isAlive() ==> "+this.isAlive());
    }
}
