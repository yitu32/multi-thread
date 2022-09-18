package com.yitu32.thread.cas;

/**
 * 用CAS实现一个安全的计数器
 */
public class CASTest {
    public static void main(String[] args) {
        CASImitation casImitation = new CASImitation(0);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                Integer andIncrement = casImitation.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + "==========" + andIncrement);
            }).start();
        }

    }
}

class CASImitation {

    private Integer value;

    public CASImitation(Integer value) {
        this.value = value;
    }

    public Integer incrementAndGet() {
        int oldValue, newValue;
        do {
            oldValue = value;
            newValue = value + 1;
        } while (!compareAndSwap(oldValue, newValue));
        return newValue;
    }

    public boolean compareAndSwap(Integer exp, Integer newValue) {
        synchronized (this){ // 这里不明白，变量加了valatile之后，还要synchronized
            if (value.equals(exp)) {
                value = newValue;
                return true;
            } else {
                return false;
            }
        }
    }

}
