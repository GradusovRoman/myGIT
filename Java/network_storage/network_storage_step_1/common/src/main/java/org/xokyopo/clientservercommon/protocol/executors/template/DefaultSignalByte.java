package org.xokyopo.clientservercommon.protocol.executors.template;

public enum DefaultSignalByte {
    ONE_STRING((byte) 0),
    FILE_LIST((byte) 1),
    FILE_OPERATION((byte) 2),
    FILE_PART((byte) 3)
    ;

    private final byte signal;

    DefaultSignalByte(byte signal) {
        this.signal = signal;
    }

    public byte getSignal() {
        return signal;
    }
}
