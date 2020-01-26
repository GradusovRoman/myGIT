package Xokyopo.hw_6.chat_server.Server.DAO.DataBase;

import java.sql.*;
import java.util.Vector;

/*
1. Сделать методы для работы с БД (CREATE, UPDATE, DELETE, INSERT, SELECT)
 */

public class DataBase_old {
    private static Connection connection;
    private static Statement statement;

    public static void connection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite3");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickName(String _login, String _password) {
        String sql = String.format("select nickname from login where login = '%s' AND password = '%s'", _login, getHash(_password));
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("nickname");
            }
        } catch (SQLException e) {
            //ошибка запроса?
            e.printStackTrace();
        }
        return null;
    }

    public static String getBlackList(String _nickName) {
        String sql = String.format("SELECT blackClient.nickname AS nickname FROM blackList\n" +
                "inner join login as client ON blackList.nickname_id = client.id\n" +
                "inner join login as blackClient ON blackList.blockName_id = blackClient.id\n" +
                "where client.nickname = '%s'", _nickName);
        try {
            ResultSet rs = statement.executeQuery(sql);
            return getString(rs, "nickname");
        } catch (SQLException e) {
            //ошибка запроса?
            e.printStackTrace();
        }
        return null;
    }

    public static void addClientToBlackList(String _nickName, String _blockClientName) {
        String sql = String.format("INSERT INTO blackList (nickname_id, blockName_id) VALUES('%s','%s')", getUserId(_nickName), getUserId(_blockClientName));
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            removeClientToBlackList(_nickName, _blockClientName);
        }
    }

    public static void removeClientToBlackList(String _nickName, String _blockClientName) {
        String sql = String.format("DELETE FROM blackList WHERE nickname_id = '%s' AND blockName_id = '%s'", getUserId(_nickName), getUserId(_blockClientName));
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            //ошибка запроса?
            //e.printStackTrace();
        }
    }

    public static String getString(ResultSet _resultSet, String _columnName) throws SQLException {
        Vector<String> vector = new Vector<>();
        while (_resultSet.next()) {
            String name = _resultSet.getString(_columnName);
            vector.add(name);
        }
        return vector.toString();
    }

    public static String getUserId(String _name) {
        String sql = String.format("SELECT id FROM login WHERE nickname = '%s'", _name);
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            //ошибка запроса?
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isLoginAreUsed(String _login) {
        String sql = String.format("SELECT id FROM login WHERE login = '%s'", _login);
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            //ошибка запроса?
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isNickAreUsed(String _nickname) {
        String sql = String.format("SELECT id FROM login WHERE nickname = '%s'", _nickname);
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            //ошибка запроса?
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addClient(String _login, String _password, String _nickname) {
        String sql = String.format("INSERT INTO login (login, password, nickname) VALUES('%s','%s','%s')", _login, getHash(_password), _nickname);
        try {
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getHash(String _password) {
        return Integer.toString(_password.hashCode());
    }

    //ДЗ ну раз не указана таблица будем работать с таблицей лог

    public static void create () {
        //создаем таблицу логов
        String sql = "CREATE TABLE log ('datetime' DATETIME NOT NULL, 'message' TEXT NOT NULL)";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete () {
        //удаляем все записи из таблицы логов
        String sql = "DELETE FROM log";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert (long _datetime, String _message) {
        //Добавляем запиись в таблицу
        String sql = String.format("INSERT INTO log ('datetime', 'message') VALUES ('%s', '%s')", _datetime, _message);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void select () {
        //получаем все записи из таблицы логов
        String sql = String.format("SELECT datetime, message FROM log");
        try {
            ResultSet rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int update (long _datetime) {
        //изменяем записи в таблице логов (просто затираем ее)
        String sql = String.format("UPDATE log SET message = '' WHERE datetime = '%s'", _datetime);
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
