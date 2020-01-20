package org.xokyopo.filesharing.DAO.Repository.Adapter;

import org.xokyopo.filesharing.Domain.Template.FilePathID;

import java.io.File;

public interface RepositoryAInput {
    FilePathID save(File file);
    File get(FilePathID filePath);
    boolean delete(FilePathID filePath);
}
