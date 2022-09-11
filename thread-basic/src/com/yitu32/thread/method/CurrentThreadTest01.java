package com.yitu32.thread.method;

/**
 * Thread.currentThread()，谁调用的该方法，就会得到谁
 */
public class CurrentThreadTest01 extends Thread {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId());
        CThread01 cThread01 = new CThread01();
        cThread01.start();
        /*
        结果：
        无参构造打印当前线程名称-Thread.currentThread().getName()：main
        run方法打印当前线程名称-Thread.currentThread().getName()：Thread-0
        分析：
        1.构造方法时，是main线程调用的，所以打印main线程
        2.调用run()方法时，是新建的一个线程，此时是新线程名称
         */
        // 如果不调用start()方法，而是直接调用run()方法：
        // cThread01.run();
        /*
        结果：
        无参构造打印当前线程名称-Thread.currentThread().getName()：main
        run方法打印当前线程名称-Thread.currentThread().getName()：main
        分析：直接调用run方法，是main方法中直接调用的，并没有开启新线程
         */
    }
}

class CThread01 extends Thread {
    public CThread01() {
        System.out.println("无参构造打印当前线程名称-Thread.currentThread().getName()：" + Thread.currentThread().getName()
                + ", this.getId():"+this.getId()
                + ", Thread.currentThread().getId():"+Thread.currentThread().getId()
        );
    }

    @Override
    public void run() {
        System.out.println("run方法打印当前线程名称-Thread.currentThread().getName()：" + Thread.currentThread().getName()
                + ", this.getId():"+this.getId()
                + ", Thread.currentThread().getId():"+Thread.currentThread().getId());
    }
}
