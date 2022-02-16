package by.academy.it.daoImpl;

import by.academy.it.dao.FriendDao;
import by.academy.it.pojo.Friends;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class FriendDaoImpl implements FriendDao {

    final static Logger logger = Logger.getLogger(FriendDaoImpl.class.getName());

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Serializable saveFriends(Friends friends) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(friends);
        logger.info("Save friend (friend: " + friends + ")");
        return id;
    }

    @Override
    public void deleteFriends(String senderId, String recipientId) {
        Session session = sessionFactory.getCurrentSession();
        Friends friends = getFriend(senderId, recipientId);
        if (friends != null) {
            session.delete(friends);
        }
        logger.info("Friend had been deleted: " + friends);
    }

    @Override
    public void updateFriends(Friends userFriends) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(userFriends);
        logger.info("Update friend (friend: " + userFriends + ")");
    }

    @Override
    public Friends getFriend(Serializable userId, Serializable friendId) {
        Session session = sessionFactory.getCurrentSession();
        List<Friends> userFriends = session.createQuery("from Friends where (friendId=:friendId and user_id=:userId) " +
                        "or (friendId=:userId and user_id=:friendId)", Friends.class)
                .setParameter("friendId", userId)
                .setParameter("userId", friendId)
                .list();
        logger.info("Read friend (friend: " + userFriends + ")");
        if (userFriends.isEmpty()) {
            return null;
        }
        return userFriends.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Friends> readAllFriends() {
        Session session = sessionFactory.getCurrentSession();
        List<Friends> userFriends = session.createQuery("from UserFriends ").list();
        logger.info("Read all friends. Friends list: " + userFriends);
        return userFriends;
    }

    @Override
    public List<Friends> readFriendsByUserId(Serializable userId) {
        Session session = sessionFactory.getCurrentSession();
        List<Friends> userFriends = session
                .createQuery("from Friends where friendId=:userId ", Friends.class)
                .setParameter("userId", userId)
                .list();
        logger.info("Read friends by user id('" + userId + "'). Friends list: " + userFriends);
        return userFriends;
    }

    @Override
    public List<Friends> readAllFriendsByUserId(Serializable userId) {
        Session session = sessionFactory.getCurrentSession();
        List<Friends> userFriends = session
                .createQuery("from Friends where friendId=:userId or user_id=:userId ", Friends.class)
                .setParameter("userId", userId)
                .list();
        logger.info("Read all friends by user id('" + userId + "'). Friends list: " + userFriends);
        if (userFriends.isEmpty()) {
            return null;
        }
        return userFriends;
    }
}
