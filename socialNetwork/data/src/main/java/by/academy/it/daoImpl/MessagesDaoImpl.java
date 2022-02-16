package by.academy.it.daoImpl;

import by.academy.it.dao.MessagesDao;
import by.academy.it.pojo.Messages;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class MessagesDaoImpl implements MessagesDao {

    final static Logger logger = Logger.getLogger(MessagesDaoImpl.class.getName());

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Serializable saveMessage(Messages message) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(message);
        logger.info("Save message (message: " + message + ")");
        return id;
    }

    @Override
    public List<Messages> readMessagesByUserIdAndFriendId(String userId, String friendId) {
        Session session = sessionFactory.getCurrentSession();
        List<Messages> messages = session
                .createQuery("from Messages where (user.id=:senderId and recipientId=:recipientId) " +
                        "or (user.id=:recipientId and recipientId=:senderId) ORDER BY timestamp", Messages.class)
                .setParameter("senderId", userId)
                .setParameter("recipientId", friendId)
                .list();
        logger.info("Read messages by user id('" + userId + "') and friend id('" + friendId + "').Messages list: " + messages);
        return messages;
    }

}
