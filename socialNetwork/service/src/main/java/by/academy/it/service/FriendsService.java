package by.academy.it.service;

import by.academy.it.dao.FriendDao;
import by.academy.it.dao.UserDao;
import by.academy.it.pojo.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FriendsService {

    @Autowired
    UserDao userDao;

    @Autowired
    FriendDao friendDao;

    @Transactional
    public String getFriendStatus(String userId, String friendId) {
        Friends userFriends = friendDao.getFriend(userId, friendId);

        if (userFriends == null) {
            return "not_status";
        }
        return userFriends.getStatus();
    }

    @Transactional
    public List<Friends> getUserFriends(String id){
        List<Friends> userFriends = friendDao.readFriendsByUserId(id);
        if (userFriends.isEmpty()) {
            return null;
        }
        return userFriends;
    }

    @Transactional
    public List<Friends> readAllUserFriends(String id){
        List<Friends> userFriends = friendDao.readAllFriendsByUserId(id);
        return userFriends;
    }

    @Transactional
    public void deleteFriends(String userId, String friendId) {
        friendDao.deleteFriends(userId, friendId);
    }

    @Transactional
    public void addFriend(String userId, String friendId, String status) {
        Friends userFriends = friendDao.getFriend(userId,friendId);
        if(userFriends==null){
            userFriends = new Friends();
        }
        userFriends.setFriendId(friendId);
        userFriends.setStatus(status);
        userFriends.setUser(userDao.readUserById(userId));
        friendDao.updateFriends(userFriends);
    }
}
