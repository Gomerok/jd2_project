package by.academy.it.controller;

import by.academy.it.dto.AuthorizedUser;
import by.academy.it.pojo.Messages;
import by.academy.it.service.MessagesService;
import by.academy.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    UserService userService;

    @Autowired
    MessagesService messagesService;


    @GetMapping(path = "/home-page/send-messages/{friendId}")
    public ModelAndView userFriendsView(@PathVariable("friendId") String friendId,
                                        @SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {
        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }
        if (userService.checkBlocked(authorizedUser.getUserId())) {
            return new ModelAndView("blocked_page");
        }

        ModelAndView modelAndView = new ModelAndView("messages");

        List<Messages> userMessages = messagesService.getMessages(authorizedUser.getUserId(), friendId);
        return modelAndView.addObject("userMessages", userMessages).addObject("friendId", friendId);
    }


    @PostMapping("/home-page/send-messages/{friendId}")
    public ModelAndView sendMessage(@PathVariable("friendId") String friendId,
                                    @SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser,
                                    @RequestParam String value) {

        ModelAndView modelAndView = new ModelAndView("messages");

        if (!value.isEmpty()) {
            messagesService.addMessage(authorizedUser.getUserId(), friendId, value, new Date());
        }

        List<Messages> userMessages = messagesService.getMessages(authorizedUser.getUserId(), friendId);

        return modelAndView.addObject("userMessages", userMessages).addObject("friendId", friendId);
    }
}
