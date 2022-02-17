package by.academy.it.dao;

import by.academy.it.pojo.Friends;

import java.io.Serializable;
import java.util.List;

public interface FriendDao {
    Serializable saveFriends(Friends friends);

    void deleteFriends(String senderId, String recipientId);

    List<Friends> readFriendsByUserId(Serializable userId);

    List<Friends> readAllFriendsByUserId(Serializable userId);

    void updateFriends(Friends friends);

    List<Friends> readAllFriends();

    Friends getFriend(Serializable userId, Serializable friendId);

    void deleteAllUserFriends(String userId);

    long countUserSubscribers(String userId);

    long countUserFriends(String userId);
}
