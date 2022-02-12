package by.academy.it.dbinit;

import by.academy.it.dao.RoleDao;
import by.academy.it.dao.UserDao;
import by.academy.it.pojo.Role;
import by.academy.it.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
            roleDao.saveRole(userRole);
        }
        if (roleDao.readRoleByName("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role(2L, "ROLE_ADMIN");
            roleDao.saveRole(adminRole);
        }
    }

    @PostConstruct
    private void userTableInit() {
        if (userDao.readUserByLogin("admin")==null) {
            User admin = new User();
            String salt = BCrypt.gensalt(12);
            String hashedPassword = BCrypt.hashpw("admin", salt);
            admin.setPassword(hashedPassword);
            admin.setLogin("admin");
            admin.setEmail("admin@gmail.com");
            admin.setActivitiStatus("ACTIVE");
            admin.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
            userDao.saveUser(admin);
        }
    }



}
