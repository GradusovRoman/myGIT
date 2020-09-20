package ru.geekbrains.thirdquarter.springintro.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:AppProperties.properties")
public class PropertiesAppliedConfig {

    private Environment environment;

    @Autowired
    public PropertiesAppliedConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(this.environment.getProperty("datasource.driver")));
        dataSource.setUrl(this.environment.getProperty("datasource.url"));
        dataSource.setUsername(this.environment.getProperty("datasource.user"));
        dataSource.setPassword(this.environment.getProperty("datasource.password"));
        return dataSource;
    }


    @Bean(name = "hibernateProperty")
    public Properties getHibernateProperties() {
        // Создание свойств для настройки Hibernate
        Properties jpaProperties = new Properties();

        // Указание диалекта конкретной базы данных
        jpaProperties.put("hibernate.dialect", this.environment.getProperty("hibernate.dialect"));

        // Указание максимальной глубины связи
        jpaProperties.put("hibernate.max_fetch_depth", this.environment.getProperty("hibernate.max_fetch_depth"));

        // Максимальное количество строк, возвращаемых за один запрос из БД
        jpaProperties.put("hibernate.jdbc.fetch_size", this.environment.getProperty("hibernate.jdbc.fetch_size"));

        // Максимальное количество запросов при использовании пакетных операций
        jpaProperties.put("hibernate.jdbc.batch_size", this.environment.getProperty("hibernate.jdbc.batch_size"));

        // Включает логирование
        jpaProperties.put("hibernate.show_sql", this.environment.getProperty("hibernate.show_sql"));

        // Форматирование вывода
        jpaProperties.put("hibernate.format_sql", this.environment.getProperty("hibernate.format_sql"));

        //Автоматически создает необходимые для работы сущности
        jpaProperties.put("hibernate.hbm2ddl.auto", this.environment.getProperty("hibernate.hbm2ddl.auto"));

        return  jpaProperties;
    }
}
