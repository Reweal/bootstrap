package ru.javamentor.springbootmvc.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javamentor.springbootmvc.model.Role;
import ru.javamentor.springbootmvc.model.User;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    boolean addRole(Role role);
    Role findByNameRole(String name);
    LinkedHashSet<Role> listRoles();
    Role findByIdRole(int id);
    Set<Role> listByRole(List<String> name);
    boolean add(User user);
    Set<User> listUsers();
    void delete(int id);
    void update(User user);
    User findById(int id);
    User findByUsername(String userName);
}
