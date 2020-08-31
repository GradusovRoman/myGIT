package ru.geekbrains.thirdquarter.springintro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "ru.geekbrains.server")
public class SpringConfig {

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dm = new DriverManagerDataSource();
        dm.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dm.setUsername("root");
        dm.setPassword("root");
        dm.setUrl("jdbc:mysql://localhost:3306/chat_server_db?serverTimezone=UTC");
        return dm;
    }
}
