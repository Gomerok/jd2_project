package by.academy.it.daoTest;

import by.academy.it.config.DaoConfiguration;
import by.academy.it.dao.FriendDao;
import by.academy.it.pojo.Friends;
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

import static org.junit.Assert.*;

@ContextConfiguration(classes = DaoConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FriendDaoTest {

    @Resource
    FriendDao friendDao;

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    @Rollback
    public void testSaveFriends() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = new User();
        user1.setLastName("user1");
        user1.setEmail("user1@gmail.com");
        User user2 = new User();
        user2.setLastName("user2");
        user2.setEmail("user2@gmail.com");

        Serializable id1 = session.save(user1);
        Serializable id2 = session.save(user2);
        Friends friends = new Friends();
        friends.setUser(user1);
        friends.setFriendId(user2.getId());
        friends.setStatus("Friend");

        friendDao.saveFriends(friends);

        assertNotNull(friends.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteFriends() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = new User();
        user1.setLastName("user1");
        user1.setEmail("user1@gmail.com");
        User user2 = new User();
        user2.setLastName("user2");
        user2.setEmail("user2@gmail.com");

        session.save(user1);
        session.save(user2);
        Friends friends = new Friends();
        friends.setUser(user1);
        friends.setFriendId(user2.getId());
        friends.setStatus("Friend");

        session.save(friends);

        Friends savedFriendNews = session.get(Friends.class, friends.getId());
        friendDao.deleteFriends(user1.getId(), user2.getId());
        Friends deletedFriend = session.get(Friends.class, friends.getId());

        assertNotNull(savedFriendNews);
        assertNull(deletedFriend);
    }

    @Test
    @Transactional
    @Rollback
    public void testReadFriendsByUserId() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = new User();
        user1.setLastName("user1");
        user1.setEmail("user1@gmail.com");
        User user2 = new User();
        user2.setLastName("user2");
        user2.setEmail("user2@gmail.com");

        session.save(user1);
        session.save(user2);
        Friends friends = new Friends();
        friends.setUser(user1);
        friends.setFriendId(user2.getId());
        friends.setStatus("Friend");

        session.save(friends);

        assertFalse(friendDao.readFriendsByUserId(user2.getId()).isEmpty());
    }

    public void testReadAllFriendsByUserId() {
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateFriends() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = new User();
        user1.setLastName("user1");
        user1.setEmail("user1@gmail.com");
        User user2 = new User();
        user2.setLastName("user2");
        user2.setEmail("user2@gmail.com");

        session.save(user1);
        session.save(user2);
        Friends friends = new Friends();
        friends.setUser(user1);
        friends.setFriendId(user2.getId());
        friends.setStatus("Friend");

        session.save(friends);
        friends.setStatus("Subscriber");

        friendDao.updateFriends(friends);

        Friends updatedFriend = session.get(Friends.class, friends.getId());
        friendDao.deleteFriends(user1.getId(), user2.getId());
        Friends deletedFriend = session.get(Friends.class, friends.getId());

        assertEquals("Subscriber", updatedFriend.getStatus());
    }

}