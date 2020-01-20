package org.xokyopo.filesharing.Domain.DepenceInjector;

import org.xokyopo.filesharing.DAO.DataBase.Adapter.DataBaseAdapter;
import org.xokyopo.filesharing.DAO.DataBase.DataBase;
import org.xokyopo.filesharing.DAO.Repository.Adapter.RepositoryAdapter;
import org.xokyopo.filesharing.DAO.Repository.Repository;
import org.xokyopo.filesharing.Domain.Function.Delete;
import org.xokyopo.filesharing.Domain.Function.Get;
import org.xokyopo.filesharing.Domain.Function.Save;
import org.xokyopo.filesharing.Domain.Function.UpdateCounter;

/*
TODO загружает все классы и связывает их между собой, это временное решенеиие пока нет лучше.
 */

public class Loader {
    private Delete delete;
    private Get get;
    private Save save;
    private UpdateCounter updateCounter;
    private Repository repository;
    private RepositoryAdapter repositoryAdapter;
    private DataBase dataBase;
    private DataBaseAdapter dataBaseAdapter;

    public Loader() {
        this.dataBase = new DataBase("D:/temp/filesharing.sqlite3");
        this.dataBase.connection();

        this.dataBaseAdapter = new DataBaseAdapter(this.dataBase);

        this.repository = new Repository("D:\\temp");

        this.repositoryAdapter = new RepositoryAdapter(this.repository);

        this.delete = new Delete(this.dataBaseAdapter, this.repositoryAdapter);

        this.get = new Get(this.dataBaseAdapter, this.repositoryAdapter);

        this.save = new Save(this.dataBaseAdapter, this.repositoryAdapter);

        this.updateCounter = new UpdateCounter(this.dataBaseAdapter, this.repositoryAdapter);
    }

    public Delete getDelete() {
        return delete;
    }

    public Get getGet() {
        return get;
    }

    public Save getSave() {
        return save;
    }

    public UpdateCounter getUpdateCounter() {
        return updateCounter;
    }

    public DataBase getDataBase() {
        return dataBase;
    }
}
