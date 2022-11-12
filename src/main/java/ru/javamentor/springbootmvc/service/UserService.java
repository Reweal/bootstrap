package ru.javamentor.springbootmvc.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javamentor.springbootmvc.model.Role;
import ru.javamentor.springbootmvc.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean addRole(Role role);
    Role findByNameRole(String name);
    List<Role> listRoles();
    Role findByIdRole(int id);
    List<Role> listByRole(List<String> name);
    boolean add(User user);
    List<User> listUsers();
    void delete(int id);
    void update(User user);
    User findById(int id);
    User findByUsername(String userName);
}
