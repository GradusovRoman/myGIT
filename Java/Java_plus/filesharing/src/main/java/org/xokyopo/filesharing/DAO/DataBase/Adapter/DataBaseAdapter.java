package org.xokyopo.filesharing.DAO.DataBase.Adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xokyopo.filesharing.Domain.Template.FilePathID;
import org.xokyopo.filesharing.Domain.Template.ID;

/**
 *Данный клас описывает взаимодейсвтие основного класса с базой данных
 */

@Component
public class DataBaseAdapter implements DataBaseAInput {
    private final DataBaseAOut dataBaseAOut;

    @Autowired
    public DataBaseAdapter(DataBaseAOut dataBaseA) {
        this.dataBaseAOut = dataBaseA;
    }

    @Override
    public ID save(FilePathID pathName) {
        ID result = this.dataBaseAOut.save(pathName);
        return result;
    }

    @Override
    public FilePathID get(ID id) {
        FilePathID result = this.dataBaseAOut.get(id);
        return result;
    }

    @Override
    public boolean update(ID id) {
        boolean result = this.dataBaseAOut.update(id);
        return result;
    }

    @Override
    public boolean delete(ID id) {
        boolean result = this.dataBaseAOut.delete(id);
        return result;
    }
}
