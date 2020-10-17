package ru.geekbrains.thirdquarter.springintro.springboot.app.domain;

import org.springframework.stereotype.Service;
import ru.geekbrains.thirdquarter.springintro.springboot.app.dao.RolesRepository;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Role;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.exceptions.NotFountException;

@Service
public class RolesService extends AService<RolesRepository, Role, Long> {
    @Override
    protected Role createEmptyEntity() {
        return new Role();
    }

    @Override
    public void save(Role role) {
        role.setName(role.getName().toUpperCase());
        super.save(role);
    }

    public Role getRoleByName(String name) {
        return repository.getRoleByName(name).orElseThrow(() -> new NotFountException("Role not found"));
    }
}
