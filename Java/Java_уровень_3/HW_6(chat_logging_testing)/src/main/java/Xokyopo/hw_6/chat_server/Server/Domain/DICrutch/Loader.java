package Xokyopo.hw_6.chat_server.Server.Domain.DICrutch;


import Xokyopo.hw_6.chat_server.Server.DAO.DataBase.DataBase;
import Xokyopo.hw_6.chat_server.Server.DAO.Logger.MyLogger;
import Xokyopo.hw_6.chat_server.Server.DAOAdapters.DataBaseAdapter.DataBaseAdapter;
import Xokyopo.hw_6.chat_server.Server.DAOAdapters.LoggerAsapte.LoggerAdapte;

//Костыль так как пока что неосвоил инекцию зависимостей.
public class Loader {
    public static void main(String[] arg) {
        final MyLogger myLogger = new MyLogger();
        final LoggerAdapte loggerAdapte = new LoggerAdapte(myLogger);
        // логер подготовлен

        final DataBase dataBase_ = new DataBase();
        dataBase_.connection();

        final DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(dataBase_);
        //ДБ готова к работе

    }
}
