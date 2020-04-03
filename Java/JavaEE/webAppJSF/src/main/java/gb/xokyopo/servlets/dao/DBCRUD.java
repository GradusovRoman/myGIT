package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.entity.Product;
import gb.xokyopo.servlets.dao.entity.template.AbstractProduct;
import gb.xokyopo.servlets.dao.entity.template.AddProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCRUD implements ProductRepository{
    private DBUtils dbUtils = DBUtils.getDbUtils();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String dbDriver = "org.sqlite.JDBC";
    //TODO адрес базы данный и данные аутентификации.
    private String dbURL = "jdbc:sqlite:D:/GIT/Java/JavaEE/Lesson1/db.sqlite";
    private String dbUserName;
    private String dbUserPass;


    private Connection getConnection() throws DAOException{
        try {
            this.loadDbDriver();
            if (this.dbUserName == null || this.dbUserName.equals("") || this.dbUserPass == null || this.dbUserPass.equals("")) {
                return DriverManager.getConnection(dbURL);
            } else {
                return DriverManager.getConnection(this.dbURL, dbUserName, dbUserPass);
            }
        } catch (Exception e){
//            this.logger.error("Not Server Connection");
            this.logger.info("Not Server Connection");
            throw new DAOException(this, "getConnection()", e);
        }
    }

    private void loadDbDriver() throws DAOException {
        try {
            Class.forName(this.dbDriver);
        } catch (ClassNotFoundException e) {
            this.logger.error("SQL driver not found");
            throw new DAOException(this, "loadDbDriver()", e);
        }
    }

    @Override
    public boolean add(AbstractProduct abstractProduct) throws DAOException {
        try (Connection connection = this.getConnection()){
            String tableName = abstractProduct.getClass().getSimpleName().toLowerCase();
            String fields = this.dbUtils.getFieldsWithoutID(abstractProduct);
            String[] values = this.dbUtils.getValuesWithoutID(abstractProduct).split(",");
            String sqlRequest = String.format("INSERT INTO %s (%s) VALUES(%s)", tableName, fields, this.dbUtils.getStringForAdd(values.length));

            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            for (int i = 0; i < values.length; i++) {
                statement.setString(i + 1, values[i]);
            }

            return  statement.executeUpdate() > 0 ;
        } catch (DAOException | SQLException e ) {
            this.logger.error("Error, adding to DB");
            throw new DAOException(this, "add(AbstractProduct abstractProduct)", e);
        }
    }

    @Override
    public boolean delete(AbstractProduct abstractProduct) throws DAOException {
        try (Connection connection = this.getConnection()){
            String tableName = abstractProduct.getClass().getSimpleName().toLowerCase();
            long id = abstractProduct.getId();
            String sqlRequest = String.format("DELETE FROM %s WHERE id = %s", tableName, this.dbUtils.addApostrophe("" + id));

            Statement statement = connection.createStatement();

            //TODO удаление связанных таблиц
            return statement.executeUpdate(sqlRequest) > 0;
        } catch (DAOException | SQLException e) {
            this.logger.error("Error, removing on DB");
            throw new DAOException(this, "remove(AbstractProduct abstractProduct)", e);
        }
    }

    //передаем элемент для поиска как шаблон.
    @Override
    public <T extends AbstractProduct> List<T> getAll(T abstractProduct) throws DAOException {
        List<T> outerList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            String tableName = abstractProduct.getClass().getSimpleName().toLowerCase();
            String fields = this.dbUtils.getAllFields(abstractProduct);
            String sqlRequest = String.format("SELECT %s FROM %s", fields, tableName);

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sqlRequest);
            while (result.next()) {
                //TODO как обойтипреобразование типов????
                outerList.add(this.dbUtils.getProductOutOfResultSet(result, fields, (T)abstractProduct.createNew()));
            }
        } catch (DAOException | AddProductException | SQLException e) {
            this.logger.error("Error, select in db");
            throw new DAOException(this, "getAll(AbstractProduct abstractProduct)", e);
        }

        return outerList;
    }

    @Override
    public boolean update(AbstractProduct abstractProduct) throws DAOException {
        try (Connection connection = this.getConnection()) {
            String tableName = abstractProduct.getClass().getSimpleName().toLowerCase();
            long id = abstractProduct.getId();
            String[] fields = this.dbUtils.getFieldsWithoutID(abstractProduct).split(",");
            String[] values = this.dbUtils.getValuesWithoutID(abstractProduct).split(",");
            String sqlRequest = String.format("UPDATE %s SET %s WHERE id = %s", tableName, this.dbUtils.getStringForUpdate(fields),this.dbUtils.addApostrophe("" + id));

            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            for (int i = 0; i < values.length; i++) {
                statement.setString(i + 1, values[i]);
            }

            return statement.executeUpdate() > 0;
        } catch (DAOException | SQLException e) {
            this.logger.error("Error, update on DB");
            throw new DAOException(this, "update(AbstractProduct abstractProduct)", e);
        }
    }

    //TODO сделать автоматическое создание Таблиц на основе шаблонов.
    private boolean creatDB(AbstractProduct abstractProduct) {
        return true;
    }

    public static void main(String[] args) {
//        Logger logger = LoggerFactory.getLogger("DBCRUD.class");
//        DBCRUD dbcrud = new DBCRUD();
//        Product product = new Product();

//        try {
////            product.setName("туфли");
////            product.setPrice(100);
////            product.setCurrencyType("шт");
////            product.setMeasureUnit("руб");
////            dbcrud.add(product);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }

//        try {
//            List<AbstractProduct> productList = dbcrud.getAll(product);
//            productList.forEach(element -> {
//                element.printInfo();
//            });
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
        Product product1 = new Product();
    }
}
