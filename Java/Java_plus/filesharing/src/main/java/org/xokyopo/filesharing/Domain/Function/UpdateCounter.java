package org.xokyopo.filesharing.Domain.Function;

import org.xokyopo.filesharing.DAO.DataBase.Adapter.DataBaseAdapter;
import org.xokyopo.filesharing.DAO.Repository.Adapter.RepositoryAdapter;
import org.xokyopo.filesharing.Domain.Template.ID;

public class UpdateCounter {
    private final DataBaseAdapter dataBaseAdapter;
    private final RepositoryAdapter repositoryAdapter;

    public UpdateCounter(DataBaseAdapter dataBaseA, RepositoryAdapter repositoryA) {
        this.dataBaseAdapter = dataBaseA;
        this.repositoryAdapter = repositoryA;
    }

    public boolean update(ID filePath) {
        //обновляем время хранения файла
        boolean result = this.dataBaseAdapter.update(filePath);
        return result;
    }
}
