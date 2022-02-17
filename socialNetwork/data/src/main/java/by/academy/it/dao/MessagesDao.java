package by.academy.it.dao;

import by.academy.it.pojo.Messages;

import java.io.Serializable;
import java.util.List;

public interface MessagesDao {
    Serializable saveMessage(Messages senderUserMessage);

    List<Messages> readMessagesByUserIdAndFriendId(String userId, String friendId);

    void deleteAllUserMessage(String userId);
}
