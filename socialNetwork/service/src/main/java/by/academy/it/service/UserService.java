package by.academy.it.service;

import by.academy.it.dao.RoleDao;
import by.academy.it.dao.UserDao;
import by.academy.it.dto.UserDto;
import by.academy.it.dto.UserValidDto;
import by.academy.it.dto.AuthorizedUser;
import by.academy.it.pojo.Role;
import by.academy.it.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void saveUser(UserValidDto newUser) {

        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setLogin(newUser.getLogin());
        user.setEmail(newUser.getEmail());
        user.setGender(newUser.getGender());
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));

        String salt = BCrypt.gensalt(12);
        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), salt);

        user.setPassword(hashedPassword);

        user.setProfileImageName(newUser.getProfileImageName());
        user.setCreateDate(new Date());
        user.setProfileText(newUser.getProfileText());
        user.setActivitiStatus("ACTIVE");
        userDao.saveUser(user);
    }

    @Transactional
    public AuthorizedUser getUserIdAndUserRoleByLogin(String login) {
        User user = userDao.readUserByLogin(login);
        Set<Role> userRoles = user.getRoles();
        String userRole = null;
        for (Role role : userRoles) {
            userRole = role.getName();
        }
        return new AuthorizedUser(user.getId(), userRole);
    }

    @Transactional
    public UserValidDto getRegistrationUserByUserId(String userId) {
        User currentUser = userDao.readUserById(userId);
        UserValidDto user = new UserValidDto();
        user.setFirstName(currentUser.getFirstName());
        user.setLastName(currentUser.getLastName());
        user.setLogin(currentUser.getLogin());
        user.setEmail(currentUser.getEmail());
        user.setProfileImageName(currentUser.getProfileImageName());
        user.setProfileText(currentUser.getProfileText());
        user.setGender(currentUser.getGender());
        return user;
    }

    @Transactional
    public UserDto getUserDtoByUserId(String userId) {
        User user = userDao.readUserById(userId);
        UserDto userDto = new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getGender(),
                user.getProfileImageName(),
                user.getProfileText(),
                user.getActivitiStatus());
        return userDto;
    }

    @Transactional
    public void updateUser(UserValidDto editUser, String userId) {
        User user = userDao.readUserById(userId);
        user.setFirstName(editUser.getFirstName());
        user.setLastName(editUser.getLastName());
        user.setLogin(editUser.getLogin());
        user.setEmail(editUser.getEmail());
        if (editUser.getProfileImageName() != null) {
            user.setProfileImageName(editUser.getProfileImageName());
        }
        user.setProfileText(editUser.getProfileText());
        user.setGender(editUser.getGender());
        System.out.println(user);
        userDao.updateUser(user);
    }

    @Transactional
    public void updateUserPassword(String newPassword, String userId) {
        String salt = BCrypt.gensalt(12);
        String hashedPassword = BCrypt.hashpw(newPassword, salt);
        userDao.updatePassword(hashedPassword, userId);
    }

    @Transactional
    public void deleteUser(String userId) {
        User user = userDao.readUserById(userId);
        userDao.deleteUser(user);
    }

    @Transactional
    public void blockedOrActiveUser(String id, String status) {
        userDao.updateActivitiStatus(id, status);
    }

    @Transactional
    public boolean checkBlocked(String userId) {
        String activitiStatus = userDao.readActivitiStatus(userId);
        return activitiStatus.equals("BLOCKED");
    }
}

