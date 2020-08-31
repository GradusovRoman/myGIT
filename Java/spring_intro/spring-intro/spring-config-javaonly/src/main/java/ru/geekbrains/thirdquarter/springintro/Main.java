package ru.geekbrains.thirdquarter.springintro;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.thirdquarter.springintro.server.ChatServer;
import ru.geekbrains.thirdquarter.springintro.server.User;
import ru.geekbrains.thirdquarter.springintro.server.persistance.UserRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        try {
            UserRepository userRepository = context.getBean(UserRepository.class);
            if (userRepository.getAllUsers().size() == 0) {
                userRepository.insert(new User(-1, "ivan", "123"));
                userRepository.insert(new User(-1, "petr", "345"));
                userRepository.insert(new User(-1, "julia", "789"));
            }
        } catch (SQLException e) {
            //ignore
        }

        context.getBean(ChatServer.class).start(7777);
    }
}
