package by.academy.it.controllers.controller;

import by.academy.it.dto.AuthorizedUser;
import by.academy.it.dto.UserDto;
import by.academy.it.pojo.Friends;
import by.academy.it.service.FriendsService;
import by.academy.it.service.SearchService;
import by.academy.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SubscriptionsController {

    @Autowired
    private UserService userService;

    @Autowired
    FriendsService friendsService;

    @Autowired
    SearchService searchService;

    @GetMapping(path = "/home-page/subscriptions")
    public ModelAndView userInfoView(@SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {
        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }
        if (userService.checkBlocked(authorizedUser.getUserId())) {
            return new ModelAndView("blocked_page");
        }

        ModelAndView modelAndView = new ModelAndView("subscriptions");

        List<Friends> userFriends = friendsService.getUserFriends(authorizedUser.getUserId());
        List<UserDto> subscriptionsList = new ArrayList<>();
        if (userFriends != null) {
            for (Friends friends : userFriends) {
                if (friends.getStatus().equals("subscriber")) {
                    UserDto loadedUser = searchService.getUserById(friends.getUser().getId());
                    subscriptionsList.add(loadedUser);
                }
            }

        }

        return modelAndView.addObject("subscriptionsList", subscriptionsList);
    }

    @PostMapping("/home-page/subscriptions")
    public ModelAndView userInfoDoView(
            @RequestParam(name = "add", required = false) String addFriend,
            @RequestParam(name = "reject", required = false) String rejectUser,
            @SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {

        ModelAndView modelAndView = new ModelAndView("subscriptions");

        if (addFriend != null) {
            friendsService.addFriend(authorizedUser.getUserId(), addFriend, "friend");
        }

        if (rejectUser != null) {
            friendsService.deleteFriends(authorizedUser.getUserId(), rejectUser);
        }

        List<Friends> userFriends = friendsService.getUserFriends(authorizedUser.getUserId());
        List<UserDto> subscriptionsList = new ArrayList<>();
        if (userFriends != null) {
            for (Friends friends : userFriends) {
                if (friends.getStatus().equals("subscriber")) {
                    UserDto loadedUser = searchService.getUserById(friends.getUser().getId());
                    subscriptionsList.add(loadedUser);
                }
            }

        }

        return modelAndView.addObject("subscriptionsList", subscriptionsList);
    }
}
