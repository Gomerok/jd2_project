package by.academy.it.validator;

import by.academy.it.dao.UserDao;
import by.academy.it.dto.LoginUserDto;
import by.academy.it.dto.UserValidDto;
import by.academy.it.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserValidator {

    @Autowired
    private UserDao userDao;

    @Transactional
    public Map<String, String> checkUserLoginAndEmail(UserValidDto newUser) {
        User userByLogin = userDao.readUserByLogin(newUser.getLogin());
        User userByEmail = userDao.readUserByEmail(newUser.getEmail());
        Map<String, String> validationErrors = new HashMap<>();
        if (userByLogin != null) {
            validationErrors.put("existUserLogin", "User with this username already exists");
        }
        if (userByEmail != null) {
            validationErrors.put("existUserEmail", "User with this email already exists");
        }
        return validationErrors;
    }

    @Transactional
    public Map<String, String> checkUserLoginForm(LoginUserDto loginUser) {
        User userByLogin = userDao.readUserByLogin(loginUser.getLogin());
        Map<String, String> validationErrors = new HashMap<>();
        if (userByLogin == null) {
            validationErrors.put("loginError", "Login not exist");
        } else {
            if (!checkPassword(loginUser.getPassword(), userByLogin.getId())) {
                validationErrors.put("passwordError", "Invalid password");
            }
        }
        return validationErrors;
    }

    @Transactional
    public boolean checkPassword(String password_plaintext, String userId) {
        boolean password_verified = false;

        String storedHash = userDao.readUserById(userId).getPassword();

        if (null == storedHash || !storedHash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, storedHash);

        return password_verified;
    }


}
