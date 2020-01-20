package org.xokyopo.filesharing.DAO.DataBase;

import org.xokyopo.filesharing.DAO.DataBase.Adapter.DataBaseAOut;
import org.xokyopo.filesharing.Domain.Template.DBTable;
import org.xokyopo.filesharing.Domain.Template.FilePathID;
import org.xokyopo.filesharing.Domain.Template.ID;


import java.sql.*;

/*
работа с базой данных
 */

public class DataBase implements DataBaseAOut {
    //TODO переместить обработчики исключений
    private Connection connection;
    private Statement statement;
    private final String DBCassName = "org.sqlite.JDBC";
    private final String DBName;
    private final String connectionString;

    public DataBase(String bdName) {
        //TODO подключение к базе данных
        this.DBName = bdName;
        this.connectionString = String.format("jdbc:sqlite:%s", this.DBName);
        this.connection();
    }

    public void connection() {
        try {
            Class.forName(this.DBCassName);
            this.connection = DriverManager.getConnection(this.connectionString);
            this.statement = this.connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("не найдена библиотека бызы данных");
            e.printStackTrace();
        }
    }

    public void disconnection() {
        try {
            this.statement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ID save(FilePathID pathName) {
        //TODO сохраняем вайлПасИД репозитория выдаем ИД для пользователя
        final long DateTime = System.currentTimeMillis(); //текущее время
        String request = String.format("INSERT INTO %s (%s, %s) VALUES ('%s' , '%s')", DBTable.TABLENAME.getName(), DBTable.VALUE.getName(), DBTable.DATETIME.getName(), pathName.getID(), DateTime);
        try {
            this.statement.executeUpdate(request);
            //TODO запрос нужно сделать одной строкой
            request = String.format("SELECT %s FROM %s WHERE %s = '%s'", DBTable.IDFIELD.getName(), DBTable.TABLENAME.getName(), DBTable.VALUE.getName(), pathName.getID());
            ResultSet rs = this.statement.executeQuery(request);
            while (rs.next()) {
                return new ID(rs.getString(DBTable.IDFIELD.getName()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FilePathID get(ID id) {
        //TODO выдаем ИД для получения файла из репозитория
        //TODO место требует защиты от SQLИнекций
        try {
            PreparedStatement pstm = this.connection.prepareStatement(String.format("SELECT %s FROM %s WHERE %s = ?", DBTable.VALUE.getName(), DBTable.TABLENAME.getName(), DBTable.IDFIELD.getName()));
            pstm.setString(1, id.getId());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new FilePathID(rs.getString(DBTable.VALUE.getName()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(ID id) {
        //TODO обновляем время хранения фала
        //TODO место требует защиты от SQLИнекций
        final long DateTime = System.currentTimeMillis(); //текущее время

        try {
            PreparedStatement pstm = this.connection.prepareStatement(String.format("UPDATE %s SET %s = '%s' WHERE %s = ?", DBTable.TABLENAME.getName(), DBTable.DATETIME.getName(), DateTime, DBTable.IDFIELD.getName()));
            pstm.setString(1, id.getId());
            if (pstm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(ID id) {
        //TODO обновляем время хранения фала

        String request = String.format("DELETE FROM %s WHERE %s = '%s'", DBTable.TABLENAME.getName(), DBTable.IDFIELD.getName(), id.getId());

        try {
            if (this.statement.executeUpdate(request) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
