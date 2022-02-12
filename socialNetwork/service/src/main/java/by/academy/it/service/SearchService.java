package by.academy.it.service;

import by.academy.it.dao.UserDao;
import by.academy.it.dto.UserDto;
import by.academy.it.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private UserDao userDao;

    public UserDto getUserById(String id) {
        User user = userDao.readUserById(id);
        UserDto resultUser = new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getGender(),
                user.getProfileImageName(),
                user.getProfileText(),
                user.getActivitiStatus());
        return resultUser;
    }

    public List<UserDto> searchAmongAll(String userId, String searchParam, int pageId, int pageSize) {
        if (searchParam == null) {
            searchParam = "";
        }

        List<UserDto> results = new ArrayList<>();
        final List<User> allUser = userDao.getUsersBySearchParam(searchParam, pageId, pageSize);
        results.addAll(allUser.stream().filter(user -> !user.getId().equals(userId)
                        && user.getRoles().stream().filter(role -> role.getName().equals("ROLE_ADMIN")).count() != 1)
                .map(user -> new UserDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getGender(),
                        user.getProfileImageName(),
                        user.getProfileText(),
                        user.getActivitiStatus()
                )).collect(Collectors.toList()));
        return results;
    }

    public long pageCount(String searchParam) {
        if (searchParam == null) {
            searchParam = "";
        }
        return userDao.countUser(searchParam);
    }

}
