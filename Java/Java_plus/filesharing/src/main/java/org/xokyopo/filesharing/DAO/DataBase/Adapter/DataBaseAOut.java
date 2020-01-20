package org.xokyopo.filesharing.DAO.DataBase.Adapter;

import org.xokyopo.filesharing.Domain.Template.FilePathID;
import org.xokyopo.filesharing.Domain.Template.ID;

public interface DataBaseAOut {
    ID save(FilePathID pathName);
    FilePathID get(ID id);
    boolean update(ID id);
    boolean delete(ID id);
}
