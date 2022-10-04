package com.yitu32.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 字段更新器
 * 注意几点：
 * 1.字段必须使用valotile修饰
 * 2.只能是实例变量，不能是静态变量
 * 3.不能用final修饰
 */
public class AtomicFieldTest {
    public static void main(String[] args) {
        User ming = new User("ming", 3);
        AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        for (int i = 0; i < 100; i++) {
            new Thread(() -> updater.getAndIncrement(ming)
            ).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ming.getAge());
    }
}

class User {
    String name;
    volatile int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
