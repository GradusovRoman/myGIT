package org.xokyopo.filesharing.Domain.Function;

import org.xokyopo.filesharing.DAO.DataBase.Adapter.DataBaseAdapter;
import org.xokyopo.filesharing.DAO.Repository.Adapter.RepositoryAdapter;
import org.xokyopo.filesharing.Domain.Template.ID;

import java.io.File;

public class Get {
    private final DataBaseAdapter dataBaseAdapter;
    private final RepositoryAdapter repositoryAdapter;

    public Get(DataBaseAdapter dataBaseA, RepositoryAdapter repositoryA) {
        this.dataBaseAdapter = dataBaseA;
        this.repositoryAdapter = repositoryA;
    }

    public File get(ID filePath) {
        //получаем запрос на выдачу файла
        File file = this.repositoryAdapter.get(this.dataBaseAdapter.get(filePath));
        return file;
    }

}
