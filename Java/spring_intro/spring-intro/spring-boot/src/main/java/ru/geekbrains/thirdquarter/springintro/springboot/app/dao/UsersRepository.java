package ru.geekbrains.thirdquarter.springintro.springboot.app.dao;

import org.springframework.stereotype.Repository;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.User;

@Repository
public interface UsersRepository extends ARepository<User, Long> {
}
