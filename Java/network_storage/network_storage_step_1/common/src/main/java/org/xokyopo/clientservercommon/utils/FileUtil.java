package org.xokyopo.clientservercommon.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {
    public static String getHumanFileLength(double fileLength) {
        float baseLength = 1024;
        String[] prefixes = new String[]{"B", "KB", "MB", "GB", "TB", "PB"};
        int count;
        for (count = 0; fileLength >= baseLength; count++) {
            fileLength = fileLength/baseLength;
        }
        return String.format("%.2f %s", fileLength , prefixes[count]);
    }

    public static void recurseDelete(Path path) throws IOException {
        List<Path> pathList = Files.walk(path).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (Path file: pathList) {
            Files.deleteIfExists(file);
        }
    }
}
