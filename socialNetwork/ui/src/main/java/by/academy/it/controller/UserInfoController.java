package by.academy.it.controller;

import by.academy.it.dto.AuthorizedUser;
import by.academy.it.dto.UserDto;
import by.academy.it.pojo.UserNews;
import by.academy.it.service.FriendsService;
import by.academy.it.service.SearchService;
import by.academy.it.service.UserNewsService;
import by.academy.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserInfoController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private UserNewsService userNewsService;

    @GetMapping(path = "/home-page/search-user/user-info/{userId}")
    public ModelAndView userInfoView(@PathVariable("userId") String friendId,
                                     @SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {
        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }

        if (userService.checkBlocked(authorizedUser.getUserId())) {
            return new ModelAndView("blocked_page");
        }
        String status = friendsService.getFriendStatus(authorizedUser.getUserId(), friendId);

        ModelAndView modelAndView = new ModelAndView("user-info");
        UserDto user = searchService.getUserById(friendId);

        List<UserNews> userNews = userNewsService.getNews(friendId);
        if (userNews != null) {
            modelAndView.addObject("userNews",userNews);
        }

        return modelAndView.addObject("user", user).addObject("addFriendStatus",status);
    }

    @PostMapping("/home-page/search-user/user-info/{userId}")
    public ModelAndView userInfoDoView(@PathVariable("userId") String friendId,
                                       @RequestParam("addFriendStatus") String status,
                                       @SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {


        UserDto friend = searchService.getUserById(friendId);
        ModelAndView modelAndView = new ModelAndView("user-info").addObject("user", friend);

        List<UserNews> userNews = userNewsService.getNews(friendId);
        if (userNews != null) {
            modelAndView.addObject("userNews",userNews);
        }

        if(authorizedUser.getUserRole().equals("ROLE_ADMIN") && status.equals("not_status")){
            friendsService.addFriend(authorizedUser.getUserId(), friendId, "friend");
            return modelAndView.addObject("addFriendStatus" , "friend");
        }

        if(status.equals("not_status")){
            friendsService.addFriend(authorizedUser.getUserId(), friendId, "subscriber");
            return modelAndView.addObject("addFriendStatus" , "subscriber");
        }

        if(status.equals("subscriber")){
            friendsService.deleteFriends(authorizedUser.getUserId(), friendId);
            return modelAndView.addObject("addFriendStatus" , "not_status");
        }

        if(status.equals("friend")){
            friendsService.deleteFriends(authorizedUser.getUserId(), friendId);
            return modelAndView.addObject("addFriendStatus" , "not_status");
        }

        return modelAndView.addObject("user", friend);
    }
}
