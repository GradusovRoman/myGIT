package org.xokyopo.filesharing.DAO.Repository.Adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xokyopo.filesharing.Domain.Template.FilePathID;

import java.io.File;

/**
 * Данный клас отвечает за сохранение наших данных и выдачу ссулок на их получение
 * Он описывает взаимодействие основного класса с репозиторием файлов
 */

@Component
public class RepositoryAdapter implements RepositoryAInput {
    private final RepositoryAOut repositoryAOut;

    @Autowired
    public RepositoryAdapter(RepositoryAOut repositoryA) {
        this.repositoryAOut = repositoryA;
    }

    @Override
    public FilePathID save(MultipartFile file) {
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
