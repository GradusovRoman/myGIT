package org.xokyopo.filesharing.Domain.Function;

import org.xokyopo.filesharing.DAO.DataBase.Adapter.DataBaseAdapter;
import org.xokyopo.filesharing.DAO.Repository.Adapter.RepositoryAdapter;
import org.xokyopo.filesharing.Domain.Template.ID;

public class Delete {
    private final DataBaseAdapter dataBaseAdapter;
    private final RepositoryAdapter repositoryAdapter;

    public Delete(DataBaseAdapter dataBaseA, RepositoryAdapter repositoryA) {
        this.dataBaseAdapter = dataBaseA;
        this.repositoryAdapter = repositoryA;
    }

    public boolean delete(ID filePath) {
        boolean result = this.repositoryAdapter.delete(this.dataBaseAdapter.get(filePath));
        if (result) {
            this.dataBaseAdapter.delete(filePath);
        }
        return result;
    }
}
