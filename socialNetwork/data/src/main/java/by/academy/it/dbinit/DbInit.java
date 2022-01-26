package by.academy.it.dbinit;

import by.academy.it.dao.RoleDao;
import by.academy.it.dao.UserDao;
import by.academy.it.pojo.Role;
import by.academy.it.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class DbInit {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @PostConstruct
    private void roleTableInit() {
        if (roleDao.readRoleByName("ROLE_USER").isEmpty()) {
            Role userRole = new Role(1L, "ROLE_USER");
            roleDao.addRole(userRole);
        }
        if (roleDao.readRoleByName("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role(2L, "ROLE_ADMIN");
            roleDao.addRole(adminRole);
        }
    }

    @PostConstruct
    private void userTableInit() {
        if (userDao.readUserByLogin("admin").isEmpty()) {
            User admin = new User();

            admin.setPassword("admin");
            admin.setLogin("admin");
            admin.setEmail("admin@gmail.com");

            admin.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
            userDao.addUser(admin);
        }
    }



}
