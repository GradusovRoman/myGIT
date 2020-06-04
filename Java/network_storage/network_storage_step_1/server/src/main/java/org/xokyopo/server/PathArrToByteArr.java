package org.xokyopo.server;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathArrToByteArr {
    public byte[] getDirList(String consumer) throws IOException {
        Path path = Paths.get(consumer);
        List<String> files;

        try (Stream<Path> stream = Files.list(path)) {
            files = stream.filter(Files::isRegularFile)
                    .map(Path::getFileName).map(Path::toString)
                    .collect(Collectors.toList());
        }

        Files.list(path).map(Path::getFileName).map(Path::toString).map(String::getBytes);

//        Stream<Path> list = Files.list(path);
        byte[] arr = null;
        byte[] line = "-".getBytes(CharsetUtil.UTF_8);

        for (String file : files) {
            byte[] arr1 = file.getBytes(CharsetUtil.UTF_8);
//            arr = ArrayUtils.addAll(arr, line);
//            arr = ArrayUtils.addAll(arr, arr1);
        }
        //ByteBuf buf = Unpooled.wrappedBuffer(arr);

        System.out.println("\n* ServerFileController. Список файлов клиента: " + files);

        return arr;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Program Files (x86)\\Microsoft.NET\\Primary Interop Assemblies");
        String [] arr = Files.list(path).map(Path::getFileName).map(Path::toString).toArray(String[]::new);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(Arrays.toString(arr).getBytes(CharsetUtil.UTF_8)));
        Arrays.toString(
                Files
                        .list(path)//список объектов(Path)
                        .map(Path::getFileName)//список имен объектов(Path)
                        .map(Path::toString)//список имен объектов(String)
                        .toArray(String[]::new)//массив строк
        ).getBytes(CharsetUtil.UTF_8); //получаем байтовый массив из строкового
    }
}
