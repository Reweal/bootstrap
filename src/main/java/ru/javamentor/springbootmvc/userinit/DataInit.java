//package ru.javamentor.springbootmvc.userinit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.javamentor.springbootmvc.model.Role;
//import ru.javamentor.springbootmvc.model.User;
//import ru.javamentor.springbootmvc.service.UserService;
//import ru.javamentor.springbootmvc.service.UserServiceImpl;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class DataInit {
//
//    private final UserService userService;
//
//    @Autowired
//    public DataInit(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostConstruct
//    public void init() {
//        Role role1 = new Role("ROLE_ADMIN");
//        Role role2 = new Role("ROLE_USER");
//
//        userService.addRole(role1);
//        userService.addRole(role2);
//
//        Set<Role> roleAdmin = new HashSet<>();
//        Set<Role> roleUser = new HashSet<>();
//
//        roleAdmin.add(role1);
//        roleUser.add(role2);
//
//        User user1 = new User(1, "admin", 12, "admin", "{bcrypt}$2a$12$05x/UGKM4U2I0LVmytyXFeXSd8gkk5OU6Hpv8bD7JDlNbP/DwfkWG\n", roleAdmin);
//        User user2 = new User(2, "user",  13,"user", "{bcrypt}$2a$12$05x/UGKM4U2I0LVmytyXFeXSd8gkk5OU6Hpv8bD7JDlNbP/DwfkWG\n", roleUser);
//
//        userService.add(user1);
//        userService.add(user2);
//    }
//}