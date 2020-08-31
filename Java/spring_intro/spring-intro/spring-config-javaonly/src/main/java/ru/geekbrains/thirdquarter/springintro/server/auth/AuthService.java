package ru.geekbrains.thirdquarter.springintro.server.auth;

import ru.geekbrains.thirdquarter.springintro.server.User;

public interface AuthService {

    boolean authUser(User user);
}
