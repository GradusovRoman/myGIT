package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.entity.template.AbstractProduct;
import gb.xokyopo.servlets.dao.entity.template.AddProductException;

import java.sql.ResultSet;
import java.sql.SQLException;

//TODO подумать как лучше поступить с ИД

public class DBUtils {
    private static DBUtils dbUtils;

    public static DBUtils getDbUtils() {
        if (DBUtils.dbUtils == null) {
            DBUtils.dbUtils = new DBUtils();
        }
        return DBUtils.dbUtils;
    }

    public String getAllFields(AbstractProduct abstractProduct) {
        return "id," + this.getFieldsWithoutID(abstractProduct);
    }

    public String getFieldsWithoutID(AbstractProduct abstractProduct) {
        //TODO переписать с использованием рефлекии
        return "name,price,currencyType,measureUnit";
    }

    public String getValuesWithoutID(AbstractProduct abstractProduct) {
        //TODO переписать с использованием рефлексии
        StringBuffer outerString = new StringBuffer();
        outerString.append(abstractProduct.getName());
        outerString.append(",").append(abstractProduct.getPrice());
        outerString.append(",").append(abstractProduct.getCurrencyType());
        outerString.append(",").append(abstractProduct.getMeasureUnit());
        return outerString.toString();
    }

    public String addApostrophe(String text) {
        return "'" + text.replace(",", "','") + "'";
    }

    public String getStringForUpdate(String[] fields) {
        StringBuffer outerString = new StringBuffer();
        for (int i = 0; i < fields.length; i++) {
            outerString
                    .append(fields[i])
                    .append(" = ?")
                    .append((i < fields.length -1) ? ", " : "");
        }
        return outerString.toString();
    }

    /*
    private long id;
    private String name;
    private int price;
    private String currencyType;
    private String measureUnit;
     */

    public <T extends AbstractProduct> T getProductOutOfResultSet(ResultSet resultSet, String fields, T product) throws SQLException, AddProductException {
        //TODO создаем класс снужными нам полями.
        String[] fieldsArray = fields.split(",");
        //TODO устанавливаем значения полей. (пока так после перепишу с использованием рефлексии или чего то поинтересней.)
        product.setId(resultSet.getLong(fieldsArray[0]));
        product.setName(resultSet.getString(fieldsArray[1]));
        product.setPrice(resultSet.getInt(fieldsArray[2]));
        product.setCurrencyType(resultSet.getString(fieldsArray[3]));
        product.setMeasureUnit(resultSet.getString(fieldsArray[4]));

        return product;
    }

    public String getStringForAdd(int length) {
        StringBuffer outerString = new StringBuffer();
        for (;length > 0; length--) {
            outerString.append("?");
            if (length > 1) outerString.append(",");
        }
        return outerString.toString();
    }

    /*
    //todo создание таблицы
    CREATE TABLE product (
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    name         STRING  NOT NULL,
    price        INT     NOT NULL,
    currencyType STRING  NOT NULL,
    measureUnit  STRING  NOT NULL
    );
     */
}
