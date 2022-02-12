package by.academy.it.controller;

import by.academy.it.dto.AuthorizedUser;
import by.academy.it.dto.UserDto;
import by.academy.it.pojo.Friends;
import by.academy.it.service.FriendsService;
import by.academy.it.service.SearchService;
import by.academy.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FriendsListController {

    @Autowired
    UserService userService;

    @Autowired
    FriendsService friendsService;

    @Autowired
    SearchService searchService;

    @GetMapping(path = "/home-page/friends-list")
    public ModelAndView userFriendsView(@SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {
        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }
        if (userService.checkBlocked(authorizedUser.getUserId())) {
            return new ModelAndView("blocked_page");
        }
        ModelAndView modelAndView = new ModelAndView("friends-list");

        List<Friends> userFriends = friendsService.readAllUserFriends(authorizedUser.getUserId());
        List<UserDto> friendsList = new ArrayList<>();
        if (userFriends != null) {
            for (Friends friends : userFriends) {
                if (friends.getStatus().equals("friend")) {
                    if (friends.getFriendId().equals(authorizedUser.getUserId())) {
                        UserDto loadedUser = searchService.getUserById(friends.getUser().getId());
                        friendsList.add(loadedUser);
                    } else {
                        UserDto loadedUser = searchService.getUserById(friends.getFriendId());
                        friendsList.add(loadedUser);
                    }
                }
            }

        }

        return modelAndView.addObject("friendsList", friendsList);
    }
}
