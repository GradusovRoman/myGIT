package org.xokyopo.filesharing.Domain.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xokyopo.filesharing.DAO.DataBase.Adapter.DataBaseAdapter;
import org.xokyopo.filesharing.DAO.Repository.Adapter.RepositoryAdapter;
import org.xokyopo.filesharing.Domain.Template.ID;

import java.io.File;

@Service
public class Save {
    private final DataBaseAdapter dataBaseAdapter;
    private final RepositoryAdapter repositoryAdapter;

    @Autowired
    public Save(DataBaseAdapter dataBaseA, RepositoryAdapter repositoryA) {
        this.dataBaseAdapter = dataBaseA;
        this.repositoryAdapter = repositoryA;
    }

    public ID save(MultipartFile file) {
        //получаем данные что бы сохранить. сначала записываем их в репозиторий потом передаем в базу данных
        ID result = this.dataBaseAdapter.save(this.repositoryAdapter.save(file));
        return result;
    }
}
