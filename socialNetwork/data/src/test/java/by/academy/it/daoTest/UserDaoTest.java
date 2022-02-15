package by.academy.it.daoTest;

import by.academy.it.config.DaoConfiguration;
//import by.academy.it.config.DaoConfigurationTest;
import by.academy.it.dao.UserDao;
import by.academy.it.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;


@ContextConfiguration(classes = DaoConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(value = false)
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    public void testCountUser() {
    }

    @Test

    public void testSaveUser() {
        User user = new User();
        String salt = BCrypt.gensalt(12);
        String hashedPassword = BCrypt.hashpw("user", salt);
        user.setPassword(hashedPassword);
        user.setLogin("user");
        user.setEmail("user@gmail.com");
        user.setActivitiStatus("ACTIVE");
//        user.setRoles(Collections.singleton(new Role(2L, "USer_ADMIN")));
        userDao.saveUser(user);

        assertNotNull(user.getId());
    }

    public void testReadAllUser() {
    }

    public void testReadUserById() {
    }

    public void testUpdateUser() {
    }

    public void testDeleteUser() {
    }

    public void testReadUserByLogin() {
    }

    public void testReadUserByEmail() {
    }

    public void testGetUsersBySearchParam() {
    }

    public void testUpdateActivitiStatus() {
    }

    public void testReadActivitiStatus() {
    }

    public void testUpdatePassword() {
    }

    public void testReadUsersByRole() {
    }
}