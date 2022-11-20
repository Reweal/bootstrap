package ru.javamentor.springbootmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.springbootmvc.model.Role;
import ru.javamentor.springbootmvc.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User findByName(String username) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.username = :id", User.class)
                .setParameter("id", username)
                .getResultList().stream().findAny().orElse(null);
    }

    public  void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public boolean add(User user) {
        entityManager.persist(user);
        return true;
    }

    public Set<User> listUsers() {
        return new LinkedHashSet<>(entityManager.createQuery("select s from User s join fetch s.roles", User.class).getResultList());
    }

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }
}