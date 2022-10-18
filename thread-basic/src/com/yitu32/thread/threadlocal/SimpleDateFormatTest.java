package com.yitu32.thread.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 多线程环境下，SimpleDateFormat可能有线程安全问题
 * 重写 initialValue 方法或者用 withInitial 可以实现初始化
 */
public class SimpleDateFormatTest {
    /**
     * 全局的那个SimpleDateFormat
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new SubThread(sdf).start();
        }
    }
}

class SubThread extends Thread {
    private SimpleDateFormat sdf;

    public SubThread(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            String text = "2023-02-03 12:13:" + String.format("%02d", new Random().nextInt(60));
            System.out.println(text);
            try {
                // 使用下面这行代码进行格式解析，共享同一个SimpleDateFormat 就会出问题 java.lang.NumberFormatException
//                System.out.println(Thread.currentThread().getName() + "第" + ++i + "次格式化后的日期" + sdf.parse(text));
                // 使用ThreadLocal里面的 SimpleDateFormat 就不会有问题，因为每个线程有属于自己的 SimpleDateFormat
                System.out.println(Thread.currentThread().getName() + "第" + ++i + "次格式化后的日期" + DateFormatUtil.parse(text));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

class DateFormatUtil {
    /**
     * 为每个线程设置初始值
     */
    static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    static Date parse(String text) throws ParseException {
        return threadLocal.get().parse(text);
    }
}
