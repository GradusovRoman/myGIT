package ru.geekbrains.thirdquarter.springintro.springboot.app.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.thirdquarter.springintro.springboot.app.ExtendedBCryptPasswordEncoder;
import ru.geekbrains.thirdquarter.springintro.springboot.app.dao.UsersRepository;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Role;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.User;

import java.util.HashSet;

@Service
public class UsersService extends AService<UsersRepository, User, Long> {
    private final RolesService rolesService;
    private final ExtendedBCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(RolesService rolesService, ExtendedBCryptPasswordEncoder passwordEncoder) {
        this.rolesService = rolesService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected User createEmptyEntity() {
        return new User();
    }

    public void registration(User user) {
        if (user == null)
            throw new IllegalArgumentException("user not fount in ' public void registration(User user)'");

        if (user.getRoles() == null) user.setRoles(new HashSet<>());

        Role userDefaultRole = rolesService.getRoleByName("ROLE_USER");
        user.getRoles().add(userDefaultRole);
        if (user.getUsername().toLowerCase().equals("admin")) {
            userDefaultRole = rolesService.getRoleByName("ROLE_ADMIN");
            user.getRoles().add(userDefaultRole);
        }

        //TODO проверка есть ли пользователь такой уже.
        this.save(user);
    }

    @Override
    public void save(User entity) {
        if (!passwordEncoder.isBCryptPassword(entity.getPassword())) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        super.save(entity);
    }
}
