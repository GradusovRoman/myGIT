package ru.geekbrains.thirdquarter.springintro.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.geekbrains.thirdquarter.springintro.server.persistance.UserRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        try {
            UserRepository userRepository = context.getBean(UserRepository.class);
            if (userRepository.getAllUsers().size() == 0) {
                userRepository.insert(new User(-1, "ivan", "123"));
                userRepository.insert(new User(-1, "petr", "345"));
                userRepository.insert(new User(-1, "julia", "789"));
            }
        } catch (SQLException e) {
//            ignore
        }

        context.getBean(ChatServer.class).start(7777);
    }
}
