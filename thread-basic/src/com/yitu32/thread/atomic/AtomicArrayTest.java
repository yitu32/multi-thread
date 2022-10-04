package com.yitu32.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayTest {

    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);
        System.out.println(atomicIntegerArray);//[0, 0, 0, 0, 0]
        atomicIntegerArray.set(2, 3);
        atomicIntegerArray.set(3, 1);
        // [0, 0, 3, 1, 0]
        System.out.println(atomicIntegerArray.get(1));
        System.out.println(atomicIntegerArray.get(2));
        System.out.println(atomicIntegerArray.getAndSet(1, 10)); // 0
        System.out.println(atomicIntegerArray);// [0, 10, 3, 1, 0]
        System.out.println(atomicIntegerArray.addAndGet(3, 5)); // 6
        System.out.println(atomicIntegerArray);// [0, 10, 3, 6, 0]
        System.out.println(atomicIntegerArray.getAndAdd(2, 1)); // 3
        System.out.println(atomicIntegerArray);// [0, 10, 4, 6, 0]
        System.out.println(atomicIntegerArray.compareAndSet(2, 4, 105)); // true
        System.out.println(atomicIntegerArray);// [0, 10, 105, 6, 0]
        System.out.println(atomicIntegerArray.compareAndSet(2, 10, 111)); // false
        System.out.println(atomicIntegerArray.getAndIncrement(0)); // 0
        System.out.println(atomicIntegerArray.incrementAndGet(0)); // 2
        System.out.println(atomicIntegerArray);// [2, 10, 105, 6, 0]
        System.out.println(atomicIntegerArray.decrementAndGet(4)); // -1
        System.out.println(atomicIntegerArray.getAndDecrement(4)); // -1
        System.out.println(atomicIntegerArray);// [2, 10, 105, 6, -2]

    }

}
