package ru.geekbrains.thirdquarter.springintro.springboot.app.dao;

import org.springframework.stereotype.Repository;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Role;

import java.util.Optional;

@Repository
public interface RolesRepository extends ARepository<Role, Long> {
    Optional<Role> getRoleByName(String name);
}
