package com.yitu32.thread.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * 用生产者消费者思想 模仿入栈出栈
 */
public class ImutationStack {
    public static void main(String[] args) {
        AStack aStack = new AStack();
        Producer producer1 = new Producer(aStack);
        Producer producer2 = new Producer(aStack);
        Producer producer3 = new Producer(aStack);

        Consumer consumer1 = new Consumer(aStack);
        Consumer consumer2 = new Consumer(aStack);
        Consumer consumer3 = new Consumer(aStack);

        producer1.setName("生产者1号");
        producer2.setName("生产者2号");
        producer3.setName("生产者3号");

        consumer1.setName("消费者1号");
        consumer2.setName("消费者2号");
        consumer3.setName("消费者3号");

        producer1.start();
        producer2.start();
        producer3.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

    }
}

class AStack {
    private List<String> list = new ArrayList<>();
    private static final int MAX = 3;

    /**
     * 模拟入栈
     */
    public synchronized void push() {
        while (list.size() >= MAX) {
            System.out.println(Thread.currentThread().getName() + " begin wait...");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String data = "data-" + Math.random();
        list.add(data);
        System.out.println(Thread.currentThread().getName() + " 添加了数据" + data);
        this.notifyAll();
    }

    public synchronized void pop() {
        while (list.size() == 0) {
            System.out.println(Thread.currentThread().getName() + "begin wait...");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 数据出栈" + list.remove(0));
        this.notifyAll();
    }
}

class Consumer extends Thread {
    private AStack stack;

    public Consumer(AStack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true) {
            stack.pop();
            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread {
    private AStack stack;

    public Producer(AStack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true) {
            stack.push();
            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
