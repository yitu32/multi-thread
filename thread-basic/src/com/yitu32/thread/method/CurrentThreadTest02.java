package com.yitu32.thread.method;


/**
 * Thread.currentThread()，谁调用的该方法，就会得到谁
 */
public class CurrentThreadTest02 extends Thread {

    public static void main(String[] args) {
        CThread02 xiaomingThread = new CThread02();
        xiaomingThread.setName("xiaoming");
        xiaomingThread.start();
        // 这里因为 Thread 类
        Thread thread02 = new Thread(xiaomingThread);
        thread02.start();

        /*

        执行结果：
        无参构造打印当前线程名称-Thread.currentThread().getName()：main     ==> 主线程调用的
        无参构造打印当前线程名称-this.getName()：Thread-0                   ==> this得到当前线程
        run方法打印当前线程名称-Thread.currentThread().getName()：xiaoming  ==> run方法中得到当前线程
        run方法打印当前线程名称-this.getName()：xiaoming                    ==> run方法中得到当前线程
        run方法打印当前线程名称-Thread.currentThread().getName()：Thread-1  ==> 这里又新建了一个线程，用来启动新资源
        run方法打印当前线程名称-this.getName()：xiaoming                    ==> 构造方法中传的资源还是名称为xiaomingThread的线程

         */
    }
}

class CThread02 extends Thread {
    public CThread02() {
        System.out.println("无参构造打印当前线程名称-Thread.currentThread().getName()：" + Thread.currentThread().getName()
                + ", Thread.currentThread().getId():" + Thread.currentThread().getId());
        System.out.println("无参构造打印当前线程名称-this.getName()：" + this.getName()
                + ", this.getId():" + this.getId());
    }

    @Override
    public void run() {
        System.out.println("run方法打印当前线程名称-Thread.currentThread().getName()：" + Thread.currentThread().getName()
                + ", Thread.currentThread().getId():" + Thread.currentThread().getId());
        System.out.println("run方法打印当前线程名称-this.getName()：" + this.getName()
                + ", this.getId():" + this.getId());
    }
}
