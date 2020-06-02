package org.xokyopo.clientservercommon.executors.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static java.nio.file.StandardOpenOption.*;
//TODO файл равен 0
public class FilePartUtil {
    private final int partSize;

    public FilePartUtil(int partSize) {
        this.partSize = partSize;
    }

    public byte[] getFilePart(Path filePath, long seed) throws IOException {
        byte[] bytes = new byte[this.partSize];
        try (RandomAccessFile file = new RandomAccessFile(filePath.toFile(), "r")) {
            file.seek(seed);
            int len = file.read(bytes);
            return (this.partSize > len) ? ((len > 0) ? Arrays.copyOf(bytes, len) : null) : bytes;
        }
    }

    //return current file length;
    public long saveFilePart(Path filePath, long seed, byte[] arr) throws IOException {
        if (Files.notExists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }

        if (filePath.toFile().length() == seed && arr != null) {
            Files.write(filePath, arr, APPEND);
        }
        
        return filePath.toFile().length();
    }

    public int getPartSize() {
        return partSize;
    }

}
