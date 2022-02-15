package by.academy.it.service;

import by.academy.it.dao.UserDao;
import by.academy.it.dao.UserNewsDao;
import by.academy.it.pojo.User;
import by.academy.it.pojo.UserNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserNewsService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserNewsDao userNewsDao;

    @Transactional
    public void addUserNews(String userId, String newsHeader, String newsDescription, Date date) {

        UserNews userNews = new UserNews(userDao.readUserById(userId), newsHeader, newsDescription, date);
        userNewsDao.saveUserNews(userNews);
    }

    @Transactional
    public List<UserNews> getNews(String userId) {
        List<UserNews> userNews = userNewsDao.readUserNewsByUserId(userId);
        if(userNews.isEmpty()){
            return null;
        }
        return userNews;
    }

    @Transactional
    public void deleteNews(String newsId) {
        userNewsDao.deleteNews(newsId);
    }

    @Transactional
    public List<UserNews> getWelcomePageNews(String userRole) {
        List<User> users = userDao.readUsersByRole(userRole);
        if (users.isEmpty()) {
            return null;
        }
        List<UserNews> userNews = users.get(0).getNews();
        if(userNews.isEmpty()){
            return null;
        }
        return userNews;
    }
}
