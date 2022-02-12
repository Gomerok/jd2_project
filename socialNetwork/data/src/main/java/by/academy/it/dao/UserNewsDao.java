package by.academy.it.dao;

import by.academy.it.pojo.UserNews;

import java.io.Serializable;
import java.util.List;

public interface UserNewsDao {
    public Serializable saveUserNews(UserNews userNews);

    List<UserNews> readUserNewsByUserId(Serializable userId);

    void deleteNews(Serializable newsId);
}
