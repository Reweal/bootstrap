package ru.javamentor.springbootmvc.dao;

import ru.javamentor.springbootmvc.model.User;

import java.util.List;

public interface UserDao {
    User findByName(String username);
    void delete(int id);
    void update(User user);
    boolean add(User user);
    List<User> listUsers();
    User findById(int id);
}
