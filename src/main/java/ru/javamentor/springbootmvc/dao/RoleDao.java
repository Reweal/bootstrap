package ru.javamentor.springbootmvc.dao;

import ru.javamentor.springbootmvc.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    void add(Role user);
    Role findByIdRole(int id);
    List<Role> listRoles();
    Role findByName(String name);
    List<Role> listByName(List<String> name);
}
