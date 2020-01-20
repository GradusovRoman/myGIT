package org.xokyopo.filesharing.Domain.Function;

import org.xokyopo.filesharing.DAO.DataBase.Adapter.DataBaseAdapter;
import org.xokyopo.filesharing.DAO.Repository.Adapter.RepositoryAdapter;
import org.xokyopo.filesharing.Domain.Template.ID;

import java.io.File;

public class Save {
    private final DataBaseAdapter dataBaseAdapter;
    private final RepositoryAdapter repositoryAdapter;

    public Save(DataBaseAdapter dataBaseA, RepositoryAdapter repositoryA) {
        this.dataBaseAdapter = dataBaseA;
        this.repositoryAdapter = repositoryA;
    }


    public ID save(File file) {
        //получаем данные что бы сохранить. сначала записываем их в репозиторий потом передаем в базу данных
        ID result = this.dataBaseAdapter.save(this.repositoryAdapter.save(file));
        return result;
    }
}
