package by.academy.it.service;

import by.academy.it.dao.RoleDao;
import by.academy.it.dao.UserDao;
import by.academy.it.dto.UserCommand;
import by.academy.it.dto.AuthorizedUser;
import by.academy.it.pojo.Role;
import by.academy.it.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(UserCommand newUser) {

        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setLogin(newUser.getLogin());
        user.setEmail(newUser.getEmail());
        user.setGender(newUser.getGender());
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(newUser.getPassword());
        user.setProfileImageName(newUser.getProfileImageName());
        user.setCreateUserDate(new Date());
        user.setProfileText(newUser.getProfileText());
        user.setIsDeleted(0);
        userDao.addUser(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<User> users = userDao.readUserByLogin(username);
//
//        if (users.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return users.get(0);
//    }
//
//    public List<User> allUsers() {
//        return userDao.readAllUser();
//    }

    public AuthorizedUser getSessionTokenAndUserRoleByLogin(String login) {
        String id = userDao.findIdByLogin(login);

        List<String> userDetails = userDao.getSessionTokenAndUserRole(id);
        AuthorizedUser authorizedUser = new AuthorizedUser(userDetails.get(0), userDetails.get(1));
        return authorizedUser;
    }

    public UserCommand getUserBySessionToken(String sessionToken) {
        User currentUser = userDao.getUserByToken(sessionToken);
        UserCommand user = new UserCommand();
        user.setFirstName(currentUser.getFirstName());
        user.setLastName(currentUser.getLastName());
        user.setLogin(currentUser.getLogin());
        user.setEmail(currentUser.getEmail());
        user.setProfileImageName(currentUser.getProfileImageName());
        user.setProfileText(currentUser.getProfileText());
        user.setGender(currentUser.getGender());
        return user;
    }

    public void updateUser(UserCommand editUser, String sessionToken) {
        User user = userDao.getUserByToken(sessionToken);
        user.setFirstName(editUser.getFirstName());
        user.setLastName(editUser.getLastName());
        user.setLogin(editUser.getLogin());
        if (!editUser.getPassword().equals("password")) {
            user.setPassword(editUser.getPassword());
        }
        user.setEmail(editUser.getEmail());
        if (editUser.getProfileImageName() != null) {
            user.setProfileImageName(editUser.getProfileImageName());
        }
        user.setProfileText(editUser.getProfileText());
        user.setGender(editUser.getGender());
        userDao.update(user);
    }
}

