package org.xokyopo.filesharing.DAO.Repository;

import org.xokyopo.filesharing.DAO.Repository.Adapter.RepositoryAOut;
import org.xokyopo.filesharing.Domain.Template.FilePathID;

import java.io.*;
import java.util.Random;

/*
Определяет что и гре и как дут храниться будет храниться
 */

public class Repository implements RepositoryAOut {
    private final String path;

    public Repository(String path) {
        this.path = path;
    }

    @Override
    public FilePathID save(File file) {
        //сохраняем файл
        //для начала нужно создать уникальную папку где будем хранить файл.
        //потом в эту папку положить файл.
        String newPath = this.getNewEmptyDir(this.path);
        System.out.println("файл " + file.getAbsolutePath() + " сохранен по пути ");
        System.out.println(newPath);
        File newFile = moveFileTo(file, newPath);
        return new FilePathID(newFile.getAbsolutePath());
    }

    @Override
    public File get(FilePathID filePath) {
        //получаем файл так как мы просто выдаем ссылку когда сохраняем
        File file = new File(filePath.getID());
        return file;
    }

    @Override
    public boolean delete(FilePathID filePath) {
        //удаляем файл, потом его директорию
        File file = new File(filePath.getID());
        file.delete();
        file = new File(file.getParent());
        return file.delete();
    }

    private File moveFileTo(File fileIn, String newPath) {
        File fileOut = new File(newPath);
        fileOut.mkdir();
        fileOut = new File(newPath, fileIn.getName());
        return this.moveFile(fileIn, fileOut);
    }

    private File moveFile(File fileIn, File fileOut) {
        try {
            InputStream in = new FileInputStream(fileIn);
            OutputStream out = new FileOutputStream(fileOut);
            byte[] buffer = new byte[1024];
            int length;
            while((length = in.read(buffer)) > 0) {
                out.write(buffer, 0 ,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileOut;
    }

    private String getRandomName(int length) {
        //TODO вынести строку с набором символов
        Random random = new Random();
        String chars = "qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM1234567890";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(chars.length())));
        }
        return result.toString();
    }

    private String getNewEmptyDir(String path) {
        //TODO требует оптимизачии (String не изменяемый тип данных)
        String newPath = path;
        int startLength = 10;
        File file = new File(path);
        while (new File(newPath).exists()) {
            if (startLength < 100) startLength++;
            newPath = path +"\\"+ getRandomName(startLength);
        }
        return newPath;
    }
}
