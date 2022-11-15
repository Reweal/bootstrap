package ru.javamentor.springbootmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.springbootmvc.dao.RoleDao;
import ru.javamentor.springbootmvc.dao.UserDao;
import ru.javamentor.springbootmvc.model.Role;
import ru.javamentor.springbootmvc.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Autowired
    public UserServiceImpl(RoleDao roleDao, UserDao userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }

    @Override
    public boolean addRole(Role role) {
        Role userPrimary = roleDao.findByName(role.getRole());
        if(userPrimary != null) {return false;}
        roleDao.add(role);
        return true;
    }

    @Override
    public Role findByNameRole(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public List<Role> listRoles() {
        return roleDao.listRoles();
    }

    @Override
    public Role findByIdRole(int id) {
        return roleDao.findByIdRole(id);
    }

    @Override
    public Set<Role> listByRole(List<String> name) {
        Set<Role> listRoles = new HashSet<>();
        for (Role role : roleDao.listByName(name)) {
            listRoles.add(role);
        }
        return listRoles;
    }

    @Override
    public boolean add(User user) {
//        User userPrimary = userDao.findByName(user.getUsername());
//        if(userPrimary != null) {return false;}
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        userDao.add(user);
        return true;
    }

    @Override
    public Set<User> listUsers() {
        Set<User> setUsers = new HashSet<>();
        for (User user : userDao.listUsers()) {
            setUsers.add(user);
        }
        return setUsers;
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        User userPrimary = findById(user.getId());
        if(!userPrimary.getPassword().equals(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        }
        Set<Role> role = new HashSet<>();
        role.add(roleDao.listRoles().get(0));
        user.setRoles(role);
        userDao.update(user);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String userName) {
        return userDao.findByName(userName);
    }

//    private Collection<? extends GrantedAuthority> ath(Collection<Role> roles) {
//        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
//                .collect(Collectors.toList());
//    }
}

