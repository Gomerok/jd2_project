package by.academy.it.daoImpl;

import by.academy.it.dao.UserNewsDao;
import by.academy.it.pojo.UserNews;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository

public class UserNewsDaoImpl implements UserNewsDao {

    final static Logger logger = Logger.getLogger(UserNewsDaoImpl.class.getName());

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Serializable saveUserNews(UserNews userNews) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(userNews);
        logger.info("User news (" + userNews + ") had been saved.");
        return id;
    }

    @Override
    public List<UserNews> readUserNewsByUserId(Serializable userId) {
        Session session = sessionFactory.getCurrentSession();
        List<UserNews> userNews = session
                .createQuery("from UserNews where user_id=:userId ", UserNews.class)
                .setParameter("userId", userId)
                .list();
        logger.info("Read user news by user id('" + userId + "'). User news list: " + userNews);
        return userNews;
    }

    @Override
    public void deleteNews(Serializable newsId) {
        Session session = sessionFactory.getCurrentSession();
        UserNews userNews = session.get(UserNews.class, newsId);
        session.delete(userNews);
        UserNews loadedUserNews = session.load(UserNews.class, userNews.getId());
        if (loadedUserNews == null) {
            logger.info("User had been deleted: " + userNews);
        }
    }
}
