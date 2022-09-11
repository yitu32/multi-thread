package com.yitu32.thread.create;

/**
 * 继承的方式
 */
public class ExThread{
    public static void main(String[] args) {
        System.out.println("主线程启动...");
        // 创建线程对象
        NewThread thread = new NewThread();
        // 启动线程
        thread.start();
        /*
         * 启动线程的实质就是请求JVM运行相应的线程，这个线程具体在什么时候运行由线程调度器(Scheduler)决定
         * 注意：
         *      start()方法调用结束并不意味着子线程开始运行
         *      调用start()方法最终会执行到run()方法
         *      如果开启了多个线程，start()调用的顺序并不一定是线程启动的顺序(由调度器决定)
         */
        System.out.println("theEnd...");
    }
}
/**
 * 自定义线程类
 * 1.继承Thread类
 * 2.实现run方法，run方法就是该线程需要执行的任务
 */
class NewThread extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("我是ExThread线程中的代码...");
    }
}
