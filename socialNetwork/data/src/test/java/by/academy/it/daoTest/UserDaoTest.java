package by.academy.it.daoTest;

import by.academy.it.config.DaoConfiguration;
import by.academy.it.dao.UserDao;
import by.academy.it.pojo.Role;
import by.academy.it.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


@ContextConfiguration(classes = DaoConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    @Rollback
    public void testCheckSavedAdminAndRole() {
        Session session = sessionFactory.getCurrentSession();
        User admin = session.get(User.class, "4028c0817eb53bc3017eb55c8fb30000");
        assertNotNull(admin);
        assertEquals("admin", admin.getLogin());
        assertTrue(admin.getRoles().toString().contains("ROLE_ADMIN"));
    }

    @Test
    @Transactional
    @Rollback
    public void testCountUserBySearchParam() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = new User();
        user1.setLastName("user1");
        user1.setEmail("user1@gmail.com");
        User user2 = new User();
        user2.setLastName("user2");
        user2.setEmail("user2@gmail.com");
        User user3 = new User();
        user3.setLastName("user3");
        user3.setEmail("user3@gmail.com");

        session.save(user1);
        session.save(user2);
        session.save(user3);

        assertEquals(3, userDao.countUser(""));
        assertEquals(1, userDao.countUser("user1"));
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveUser() {
        User user = new User();
        user.setPassword("user");
        user.setLogin("user");
        user.setEmail("user@gmail.com");
        user.setActivitiStatus("ACTIVE");

        userDao.saveUser(user);

        assertNotNull(user.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void testReadAllUser() {
        Session session = sessionFactory.getCurrentSession();
        User user1 = new User();
        user1.setLogin("user1");
        user1.setEmail("user1@gmail.com");
        User user2 = new User();
        user2.setLogin("user2");
        user2.setEmail("user2@gmail.com");
        User user3 = new User();
        user3.setLogin("user3");
        user3.setEmail("user3@gmail.com");

        session.save(user1);
        session.save(user2);
        session.save(user3);

        List<User> users = userDao.readAllUser();
        assertFalse(users.isEmpty());
        assertEquals(user2.getLogin(), users.get(2).getLogin());
    }

    @Test
    @Transactional
    @Rollback
    public void testReadUserById() {
        Session session = sessionFactory.getCurrentSession();
        User user1 = new User();
        user1.setLogin("user1");
        user1.setEmail("user1@gmail.com");

        Serializable id = session.save(user1);

        User loadedUser = userDao.readUserById(id);
        assertEquals(user1.getLogin(), loadedUser.getLogin());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateUser() {
        Session session = sessionFactory.getCurrentSession();
        User user1 = new User();
        user1.setLogin("user1");
        user1.setEmail("user1@gmail.com");

        Serializable id = session.save(user1);
        user1.setLogin("user2");
        userDao.updateUser(user1);

        User loadedUser = session.get(User.class, id);
        assertEquals("user2", loadedUser.getLogin());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteUser() {
        Session session = sessionFactory.getCurrentSession();
        User user1 = new User();
        user1.setLogin("user1");
        user1.setEmail("user1@gmail.com");

        Serializable id = session.save(user1);

        assertNotNull(id);
        userDao.deleteUser(user1);
        assertNull(session.get(User.class, id));
    }

    @Test
    @Transactional
    @Rollback
    public void testReadUserByLogin() {
        Session session = sessionFactory.getCurrentSession();
        User user1 = new User();
        user1.setLogin("user1");
        user1.setEmail("user1@gmail.com");

        Serializable id = session.save(user1);
        assertNotNull(id);

        User loadedUser = userDao.readUserByLogin("user1");

        assertEquals(user1.getId(), loadedUser.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void testReadUserByEmail() {
        Session session = sessionFactory.getCurrentSession();
        User user1 = new User();
        user1.setLogin("user1");
        user1.setEmail("user1@gmail.com");

        Serializable id = session.save(user1);
        assertNotNull(id);

        User loadedUser = userDao.readUserByEmail("user1@gmail.com");

        assertEquals(user1.getId(), loadedUser.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetUsersBySearchParam() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = new User();
        user1.setLastName("user1");
        user1.setEmail("user1@gmail.com");
        User user2 = new User();
        user2.setLastName("user22");
        user2.setEmail("user2@gmail.com");
        User user3 = new User();
        user3.setLastName("user23");
        user3.setEmail("user3@gmail.com");

        session.save(user1);
        session.save(user2);
        session.save(user3);

        List<User> usersByEmpty = userDao.getUsersBySearchParam("", 1, 5);
        List<User> users1 = userDao.getUsersBySearchParam("user2", 1, 5);
        List<User> users2 = userDao.getUsersBySearchParam("user1", 1, 5);
        assertEquals(3, usersByEmpty.size());
        assertEquals(2, users1.size());
        assertEquals(1, users2.size());
    }

//    @Test
//    @Transactional
//    @Rollback
//    public void testUpdateActivitiStatus() {
//        Session session = sessionFactory.getCurrentSession();
//        User user1 = new User();
//        user1.setLogin("user1");
//        user1.setEmail("user1@gmail.com");
//        user1.setActivitiStatus("ACTIVE");
//
//        Serializable id = session.save(user1);
////        assertNotNull(id);
//        userDao.updateActivitiStatus(id, "BLOCKED");
//
//        User loadedUser = session.get(User.class, id);
//        assertEquals("BLOCKED", loadedUser.getActivitiStatus());
//    }

    @Test
    @Transactional
    @Rollback
    public void testReadActivitiStatus() {
        Session session = sessionFactory.getCurrentSession();
        User user1 = new User();
        user1.setLogin("user1");
        user1.setEmail("user1@gmail.com");
        user1.setActivitiStatus("ACTIVE");

        Serializable id = session.save(user1);
        assertNotNull(id);
        userDao.readActivitiStatus(id);

        User loadedUser = session.get(User.class, id);
        assertEquals("ACTIVE", loadedUser.getActivitiStatus());
    }

//    @Test
//    @Transactional
//    @Rollback
//    public void testUpdatePassword() {
//        Session session = sessionFactory.getCurrentSession();
//        User user1 = new User();
//        user1.setLogin("user1");
//        user1.setEmail("user1@gmail.com");
//        user1.setPassword("password");
//
//        Serializable id = session.save(user1);
//        assertNotNull(id);
//        userDao.updatePassword("password1", id);
//
//        User loadedUser = session.get(User.class, id);
//        assertEquals("password1", loadedUser.getPassword());
//    }

    @Test
    @Transactional
    @Rollback
    public void testReadUsersByRole() {
        Session session = sessionFactory.getCurrentSession();
        User user = new User();

        user.setLogin("user");
        user.setEmail("user@gmail.com");
        user.setActivitiStatus("ACTIVE");
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));

        Serializable id = session.save(user);
        assertNotNull(id);

        User loadedUser = userDao.readUsersByRole("ROLE_USER").get(0);

        assertEquals(user.getLogin(), loadedUser.getLogin());
    }
}