package by.academy.it.daoTest;

import by.academy.it.config.DaoConfiguration;
import by.academy.it.dao.MessagesDao;
import by.academy.it.pojo.Messages;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = DaoConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MessagesDaoTest {

    @Resource
    private MessagesDao messagesDao;

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    @Rollback
    public void testSaveMessage() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = new User();
        user1.setLastName("user1");
        user1.setEmail("user1@gmail.com");
        User user2 = new User();
        user2.setLastName("user2");
        user2.setEmail("user2@gmail.com");

        session.save(user1);
        session.save(user2);

        Messages messages = new Messages();
        messages.setUser(user1);
        messages.setRecipientId(user2.getId());
        messages.setValue("Value");
        messagesDao.saveMessage(messages);

        assertNotNull(messages.getId());
        List<Messages> loadedMessage = messagesDao.readMessagesByUserIdAndFriendId(user1.getId(), user2.getId());
        assertEquals("Value", loadedMessage.get(0).getValue());
    }

}