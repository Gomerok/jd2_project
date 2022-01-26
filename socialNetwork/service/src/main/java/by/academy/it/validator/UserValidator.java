package by.academy.it.validator;

import by.academy.it.dao.UserDao;
import by.academy.it.dto.UserCommand;
import by.academy.it.dto.LoginUserCommand;
import by.academy.it.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserValidator {

    @Autowired
    private UserDao userDao;

    public Map<String, String> addUserValidator(UserCommand newUser) {
        List<User> userListByLogin = userDao.readUserByLogin(newUser.getLogin());
        List<User> userListByEmail = userDao.readUserByEmail(newUser.getEmail());
        Map<String, String> validationErrors = new HashMap<>();
        if (!userListByLogin.isEmpty()) {
            validationErrors.put("existUserLogin", "User with this username already exists");
        }
        if (!userListByEmail.isEmpty()) {
            validationErrors.put("existUserEmail", "User with this email already exists");
        }
        return validationErrors;
    }

    public Map<String, String> loginUserValidator(LoginUserCommand loginUser) {
        List<User> userListByLogin = userDao.readUserByLogin(loginUser.getLogin());
        Map<String, String> validationErrors = new HashMap<>();
        if (userListByLogin.isEmpty()) {
            validationErrors.put("loginError", "Login not exist");
        } else {
            if (!userListByLogin.get(0).getPassword()
                    .equals(loginUser.getPassword())) {
                validationErrors.put("passwordError", "Invalid password");
            }
        }
        return validationErrors;
    }
}
