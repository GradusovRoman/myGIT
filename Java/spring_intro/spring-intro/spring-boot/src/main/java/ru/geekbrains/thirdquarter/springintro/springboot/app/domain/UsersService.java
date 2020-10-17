package ru.geekbrains.thirdquarter.springintro.springboot.app.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.thirdquarter.springintro.springboot.app.dao.UsersRepository;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Role;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.User;

@Service
public class UsersService extends AService<UsersRepository, User, Long> {
    private final RolesService rolesService;

    @Autowired
    public UsersService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Override
    protected User createEmptyEntity() {
        return new User();
    }

    @Override
    public void save(User user) {
        if (user.getId() <= 0) {
            Role userDefaultRole = rolesService.getRoleByName("USER");
            user.getRoles().add(userDefaultRole);
            if (user.getUsername().toLowerCase().equals("admin")) {
                userDefaultRole = rolesService.getRoleByName("ADMIN");
                user.getRoles().add(userDefaultRole);
            }
        }
        super.save(user);
    }
}
