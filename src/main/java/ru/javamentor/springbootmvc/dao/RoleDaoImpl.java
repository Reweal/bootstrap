package ru.javamentor.springbootmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.springbootmvc.model.Role;
import ru.javamentor.springbootmvc.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void add(Role user) {
        entityManager.persist(user);
    }

    @Override
    public Role findByIdRole(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> listRoles() {
        return entityManager.createQuery("select l from Role l", Role.class).getResultList();
    }

    @Override
    public Role findByName(String name) {
        return entityManager.createQuery("select u from Role u where u.role = :id", Role.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<Role> listByName(List<String> name) {
        return  entityManager.createQuery("select u from Role u where u.role in (:id)", Role.class)
                .setParameter("id", name)
                .getResultList();
    }
}
