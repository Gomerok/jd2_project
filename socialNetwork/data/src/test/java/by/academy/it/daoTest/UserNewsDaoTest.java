package by.academy.it.daoTest;

import by.academy.it.config.DaoConfiguration;
import by.academy.it.dao.UserNewsDao;
import by.academy.it.pojo.User;
import by.academy.it.pojo.UserNews;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = DaoConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserNewsDaoTest {

    @Resource
    private UserNewsDao userNewsDao;

    @Autowired
    @Qualifier("socialNetworkSessionFactory")
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    @Rollback
    public void testSaveUserNews() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = new User();
        user1.setLastName("user1");
        user1.setEmail("user1@gmail.com");
        session.save(user1);
        List<UserNews> userNewsList = new ArrayList<>();
        UserNews userNews = new UserNews();

        userNews.setUser(user1);
        userNews.setCreationTime(new Date());
        userNews.setHeader("News");
        userNews.setDescription("News desc");
        userNewsList.add(userNews);
        user1.setNews(userNewsList);

        userNewsDao.saveUserNews(userNews);

        assertNotNull(user1.getNews().get(0).getId());
        assertEquals("News desc", user1.getNews().get(0).getDescription());

    }

    @Test
    @Transactional
    @Rollback
    public void testReadUserNewsByUserId() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = new User();
        user1.setLastName("user1");
        user1.setEmail("user1@gmail.com");

        List<UserNews> userNewsList = new ArrayList<>();
        UserNews userNews = new UserNews();

        userNews.setUser(user1);
        userNews.setCreationTime(new Date());
        userNews.setHeader("News");
        userNews.setDescription("News desc");
        userNewsList.add(userNews);
        user1.setNews(userNewsList);
        session.save(user1);

        List<UserNews> loadedUserNews = userNewsDao.readUserNewsByUserId(user1.getId());
        assertFalse(loadedUserNews.isEmpty());
        assertEquals("News desc", loadedUserNews.get(0).getDescription());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteNews() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = new User();
        user1.setLastName("user1");
        user1.setEmail("user1@gmail.com");

        List<UserNews> userNewsList = new ArrayList<>();
        UserNews userNews = new UserNews();

        userNews.setUser(user1);
        userNews.setCreationTime(new Date());
        userNews.setHeader("News");
        userNews.setDescription("News desc");
        userNewsList.add(userNews);
        user1.setNews(userNewsList);
        session.save(user1);

        UserNews savedUserNews = session.get(UserNews.class, userNews.getId());
        userNewsDao.deleteNews(userNews.getId());
        UserNews deletedUserNews = session.get(UserNews.class, userNews.getId());

        assertNotNull(savedUserNews);
        assertNull(deletedUserNews);
    }
}