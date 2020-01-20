package org.xokyopo.filesharing.DAO.Repository.Adapter;

import org.xokyopo.filesharing.Domain.Template.FilePathID;

import java.io.File;

/**
 * Данный клас отвечает за сохранение наших данных и выдачу ссулок на их получение
 * Он описывает взаимодействие основного класса с репозиторием файлов
 */

public class RepositoryAdapter implements RepositoryAInput {
    private final RepositoryAOut repositoryAOut;

    public RepositoryAdapter(RepositoryAOut repositoryA) {
        this.repositoryAOut = repositoryA;
    }

    @Override
    public FilePathID save(File file) {
        FilePathID result = this.repositoryAOut.save(file);
        return result;
    }

    @Override
    public File get(FilePathID filePath) {
        File file = this.repositoryAOut.get(filePath);
        return file;
    }

    @Override
    public boolean delete(FilePathID filePath) {
        boolean result = this.repositoryAOut.delete(filePath);
        return result;
    }
}
