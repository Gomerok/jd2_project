package by.academy.it.dao;

import by.academy.it.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface UserDao {
    void addUser(User user);

    List<User> readAllUser();

    User readUserById(String id);

    String findIdByLogin(String id);

    void update(User user);

    void deleteUser(User user);

    List<User> readUserByLogin(String userLogin);

    List<User> readUserByEmail(String userEmail);

    User getUserByToken(String sessionToken);

    List<String> getSessionTokenAndUserRole(String id);


}
