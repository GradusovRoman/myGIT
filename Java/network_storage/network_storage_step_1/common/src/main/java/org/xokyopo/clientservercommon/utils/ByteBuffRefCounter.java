package org.xokyopo.clientservercommon.utils;

import io.netty.buffer.ByteBuf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ByteBuffRefCounter {
    private static final Map<String, Integer> refReleaseCounter = new ConcurrentHashMap<>();
    private static final Map<String, Integer> notRefReleaseCounter = new ConcurrentHashMap<>();

    public static final void add(String msg, ByteBuf byteBuf) {
        int refCount = byteBuf.refCnt();
        if (refCount == 0) {
            refReleaseCounter.put(msg, refReleaseCounter.getOrDefault(msg, 0) + 1);
            System.out.println(msg + " :зарелизено = " + refReleaseCounter.get(msg));
        } else {
            notRefReleaseCounter.put(msg, notRefReleaseCounter.getOrDefault(msg, 0) + refCount);
            System.out.println(msg + " :не зарелизено = " + notRefReleaseCounter.get(msg));
        }
    }

    private static void printStatus(Map<String, Integer> mapCounter, String msg) {
        mapCounter.forEach((s, integer) -> {
            System.out.println(s + msg + integer);
        });
    }

    public static void printAllStatus() {
        System.out.println("зарелизено ");
        printStatus(refReleaseCounter, " :зарелизено = ");
        System.out.println("не зарелизено ");
        printStatus(notRefReleaseCounter, " :не зарелизено = ");
    }
}
