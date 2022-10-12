package com.yitu32.thread.communication;

/**
 * 生产者消费者模型
 */
public class ProducerAndConsumerTest {

    public static void main(String[] args) {
        SourceData source = new SourceData();
        Thread t1 = new Thread(() -> {
            while (true) {
                source.set();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(() -> {
            while (true) {
                source.set();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                source.get();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t4 = new Thread(() -> {
            while (true) {
                source.get();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}


class SourceData {

    private volatile String  value = "";

    synchronized void set() {
        while (!"".equals(value)) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String value = System.nanoTime() + "";
        System.out.println(Thread.currentThread().getName() + "设置的值为=" + value);
        this.value = value;
        notifyAll();
    }

    synchronized String get() {
        while ("".equals(value)) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "取出当前value的值为=" + value);
        this.value = "";
        notifyAll();
        return value;
    }
}
