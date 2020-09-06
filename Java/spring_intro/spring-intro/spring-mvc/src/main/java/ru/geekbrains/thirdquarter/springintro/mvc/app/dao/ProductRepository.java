package ru.geekbrains.thirdquarter.springintro.mvc.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.entities.Product;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository extends EntityRepository<Product>{

    @Autowired
    public ProductRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Map<String, Object> convertEntityToFields(Product entity) {
        Map <String, Object> result = new HashMap<>();
        result.put("id", entity.getId());
        result.put("title", entity.getTitle());
        result.put("cost", entity.getCost());
        return result;
    }

    @Override
    protected Product convertResultSetToEntity(ResultSet rs) throws SQLException {
        Product result = new Product();
        result.setId(rs.getLong("id"));
        result.setTitle(rs.getString("title"));
        result.setCost(rs.getInt("cost"));
        return result;
    }

    @Override
    protected void creatIfNotExist(DataSource dataSource) {
        try (Statement statement = dataSource.getConnection().createStatement()){
            String sqlQuery = String.format("" +
                    "CREATE TABLE IF NOT EXISTS %s (" +
                    "`id` INT UNSIGNED NOT NULL AUTO_INCREMENT," +
                    "`title` VARCHAR(450) NOT NULL," +
                    "`cost` INT NOT NULL," +
                    "PRIMARY KEY (`id`)," +
                    "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);", this.getTableName());
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            System.err.println("<<creatIfNotExist>>");
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected String getTableName() {
        return "ProductRepository";
    }
}
