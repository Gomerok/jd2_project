package by.academy.it.service;

import by.academy.it.dao.UserDao;
import by.academy.it.dto.SearchResult;
import by.academy.it.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    @Autowired
    private UserDao userDao;

    public List<SearchResult> searchAmongAll(String searchParam,int pageId, int pageSize) {
        if (searchParam == null) {
            searchParam="";
        }

        List<SearchResult> results = new ArrayList<>();
        final List<User> allUser = userDao.search(searchParam, pageId, pageSize);
        results.addAll(allUser.stream()
                .map(user -> new SearchResult(
                        user.getUserId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getGender(),
                        user.getProfileImageName(),
                        user.getProfileText()
                )).collect(Collectors.toList()));
        return results;
    }

    public long pageCount(String searchParam) {
        if (searchParam == null) {
            searchParam="";
        }
        return userDao.countUser(searchParam);
    }


}
