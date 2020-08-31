package ru.geekbrains.thirdquarter.springintro.mvc.app.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public abstract class Repository<T extends Entity> {
    protected final DataSource dataSource;

    @Autowired
    public Repository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.creatIfNotExist(dataSource);

    }

    public final List<T> getAll() {
        String sqlQuery = String.format("SELECT * FROM %s", this.getTableName());
        List<T> result = new ArrayList<>();
        try (Statement statement = this.dataSource.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                result.add(convertResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            System.err.println("<<getAll>>");
            System.err.println(e.getMessage());
        }
        return result;
    }

    public final List<T> findByField(String fieldName, Object value) {
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=?", this.getTableName(), fieldName);
        List<T> result = new ArrayList<>();
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement(sqlQuery)) {
            statement.setObject(1, value);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result.add(this.convertResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            System.err.println("<<findByField>>");
            System.err.println(e.getMessage());
        }
        return result;
    }

    public final T getById(long id) {
        return this.findByField("id", id).get(0);
    }

    public final void delete(long id) {
        String sqlQuery = String.format("DELETE FROM %s WHERE id=%s", this.getTableName(), id);
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (SQLException e) {
            System.err.println("<<delete>>");
            System.err.println(e.getMessage());
        }
    }

    public final void update(T entity) {
        Map<String, Object> fields = this.convertEntityToFields(entity);
        fields.remove("id");
        String sqlQuery = String.format("UPDATE %s SET %s WHERE id=%s",
                this.getTableName(),
                fields.entrySet().stream()
                        .map(field->String.format("%s=?", field.getKey()))
                        .reduce((s1, s2) -> String.format("%s, %s", s1, s2))
                        .orElse(""),
                entity.getId()
        );

        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement(sqlQuery)) {
            int[] paramCount = new int[]{1};
            for (Map.Entry<String, Object> field: fields.entrySet()) {
                statement.setObject(paramCount[0]++, field.getValue());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("<<update>>");
            System.err.println(e.getMessage());
        }
    }

    public final void create(T entity) {
        Map<String, Object> fields = this.convertEntityToFields(entity);
        fields.remove("id"); // TODO есть или нет автоинкремент??
        String sqlQuery = String.format("INSERT %s SET %s",
                this.getTableName(),
                fields.entrySet().stream()
                        .map(field->String.format("%s=?", field.getKey()))
                        .reduce((s1, s2) -> String.format("%s, %s", s1, s2))
                        .orElse("")
        );

        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement(sqlQuery)) {
            int[] paramCount = new int[]{1};
            for (Map.Entry<String, Object> field: fields.entrySet()) {
                statement.setObject(paramCount[0]++, field.getValue());
            }
            statement.execute();
        } catch (SQLException e) {
            System.err.println("<<create>>");
            System.err.println(e.getMessage());
        }
    }

    //поля из объекта
    protected abstract Map<String, Object> convertEntityToFields(T entity);

    //объект из ResultSet
    protected abstract T convertResultSetToEntity(ResultSet rs) throws SQLException;

    //команда создания таблицы в базе
    protected abstract void creatIfNotExist(DataSource dataSource);

    protected abstract String getTableName();

}
