package by.academy.it.service;

import by.academy.it.dao.MessagesDao;
import by.academy.it.dao.UserDao;
import by.academy.it.pojo.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessagesService {

    @Autowired
    UserDao userDao;

    @Autowired
    MessagesDao messagesDao;

    public void addMessage(String userId, String friendId, String value, Date date) {

        Messages senderUserMessage = new Messages();
        senderUserMessage.setRecipientId(friendId);
        senderUserMessage.setTimestamp(date);
        senderUserMessage.setValue(value);
        senderUserMessage.setUser(userDao.readUserById(userId));
        messagesDao.saveMessage(senderUserMessage);
    }

    public List<Messages> getMessages(String userId, String friendId) {
        List<Messages> messages = messagesDao.readMessagesByUserIdAndFriendId(userId, friendId);
        return messages;
    }
}
