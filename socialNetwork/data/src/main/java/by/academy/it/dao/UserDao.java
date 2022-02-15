package by.academy.it.dao;

import by.academy.it.pojo.User;
import by.academy.it.pojo.UserNews;

import java.io.Serializable;
import java.util.List;

public interface UserDao {

    long countUser(String Param);

    Serializable saveUser(User user);

    List<User> readAllUser();

    User readUserById(Serializable id);

    void updateUser(User user);

    void deleteUser(User user);

    User readUserByLogin(String userLogin);

    User readUserByEmail(String userEmail);

    List<User> getUsersBySearchParam(String searchParam, int pageid, int total);

    int updateActivitiStatus(Serializable id, String status);

    String readActivitiStatus(Serializable id);

    int updatePassword(String newPassword, Serializable id);

    List<User> readUsersByRole(String role);

}


