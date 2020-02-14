package org.xokyopo.filesharing.DAO.Repository.Adapter;

import org.springframework.web.multipart.MultipartFile;
import org.xokyopo.filesharing.Domain.Template.FilePathID;

import java.io.File;

public interface RepositoryAInput {
    FilePathID save(MultipartFile file);
    File get(FilePathID filePath);
    boolean delete(FilePathID filePath);
}
