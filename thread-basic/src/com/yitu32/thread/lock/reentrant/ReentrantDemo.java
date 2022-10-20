package com.yitu32.thread.lock.reentrant;

public class ReentrantDemo {
    // 执行m1() 方法，锁对象为this
    public synchronized void m1(){
        System.out.println("method1...");
        /*
        在m1()中调用m2()，m2()方法的锁对象仍然为 this，但是此时 this锁并没被m1()方法释放
        由于this锁是被当前线程持有的，所以，可以再次获得this对象，这就是锁的可重入性
        假设这里不可重入，就会造成死锁
         */
        m2();
    }

    private synchronized void m2() {
        System.out.println("method2...");
        m3();
    }

    private synchronized void m3() {
        System.out.println("method3...");
    }

    public static void main(String[] args) {
        ReentrantDemo demo = new ReentrantDemo();
        new Thread(() -> demo.m1()).start();
    }
}
