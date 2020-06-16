package org.xokyopo.clientservercommon.utils.mybuffer;

import java.util.Arrays;

public class SimpleBuffer {
    public static final int CHAR_LENGTH = 2;
    public static final int INT_LENGTH = 4;
    public static final int LONG_LENGTH = 8;
    public static final int FLOAT_LENGTH = 4;
    public static final int DOUBLE_LENGTH = 8;

    private BufferPool bufferPool;

    private int start;
    private int stop;

    private int readSeed;
    private int writeSeed;


    private byte[] getByteArr() {
        //TODO берет эти данные из bufferPool
        return null;
    }

    public int readableByte() {
        return this.writeSeed - this.readSeed;
    }

    public int writableByte() {
        return this.capacity() - this.writeSeed;
    }

    public int capacity() {
        return this.stop - this.start;
    }

    private void canWrite(int length) {
        if (this.writableByte() < length)
            throw new ArrayIndexOutOfBoundsException("SimpleBuffer not FreeSpace");

    }

    private void canRead(int length) {
        if (this.readableByte() < length)
            throw new ArrayStoreException("SimpleBuffer hav not byte : " + this.readableByte());
    }

    private void write(byte[] byteArr) {
        System.arraycopy(byteArr, 0, this.getByteArr(), this.start + this.writeSeed, byteArr.length);
        this.writeSeed += byteArr.length;
    }

    private byte[] read(int length) {
        byte[] result = Arrays.copyOfRange(this.getByteArr(), this.start + this.readSeed, this.start + this.readSeed + length);
        this.readSeed += length;
        return result;
    }

    public void writeByte(byte i) {
        this.canWrite(1);
        this.getByteArr()[this.writeSeed] = i;
        this.writeSeed++;
    }

    public byte readByte() {
        this.canRead(1);
        this.readSeed++;
        return this.getByteArr()[this.readSeed - 1];
    }

    public void writeBytes(byte[] bytes) {
        this.canWrite(bytes.length);
        this.write(bytes);
    }

    public byte[] readBytes(int length) {
        this.canRead(length);
        return this.read(length);
    }

    public void writeInt(int i) {
        this.canWrite(INT_LENGTH);
        for (int j = (INT_LENGTH - 1) * 8; j >= 0; j-=8) {
            this.getByteArr()[this.writeSeed++] = (byte)((i >> j) & 0xff);
        }
    }

    public int readInt() {
        this.canRead(INT_LENGTH);
        int result = 0;
        for (int i = (INT_LENGTH - 1) * 8; i >= 0; i-=8) {
            result |= (int)(0xff & this.getByteArr()[this.readSeed++]) << i;
        }
        return result;
    }
    //TODO
}
