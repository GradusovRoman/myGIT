package org.xokyopo.clientservercommon.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class ByteBuffCounter {
    private static AtomicInteger count = new AtomicInteger();

    public static void increment() {
        count.incrementAndGet();
    }

    public static void decrement() {
        int a = count.decrementAndGet();
        System.out.println("в памяти " + a + " ByteBuf");
    }
}
