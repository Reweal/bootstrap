//package ru.javamentor.springbootmvc.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import ru.javamentor.springbootmvc.dao.RoleDao;
//import ru.javamentor.springbootmvc.model.Role;
//
//import java.util.List;
//
//public class RoleServiceImpl implements RoleService {
//
//    private final RoleDao roleDao;
//
//    @Autowired
//    public RoleServiceImpl(RoleDao roleDao) {
//        this.roleDao = roleDao;
//    }
//
//    @Override
//    public List<Role> getRoleList() {
//        return roleDao.listRoles();
//    }
//}
