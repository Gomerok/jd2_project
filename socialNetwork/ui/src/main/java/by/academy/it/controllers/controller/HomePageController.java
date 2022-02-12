package by.academy.it.controllers.controller;

import by.academy.it.dto.AuthorizedUser;
import by.academy.it.dto.UserDto;
import by.academy.it.pojo.UserNews;
import by.academy.it.service.UserNewsService;
import by.academy.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


@Controller
public class HomePageController {

    @Autowired
    UserNewsService userNewsService;

    @Autowired
    UserService userService;

    @GetMapping("/home-page")
    public ModelAndView homeView(@SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {

        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }
        if (userService.checkBlocked(authorizedUser.getUserId())) {
            return new ModelAndView("blocked_page");
        }

        ModelAndView homeView = new ModelAndView("home-page");
        UserDto currentUser = userService.getUserDtoByUserId(authorizedUser.getUserId());
        homeView.addObject("currentUser", currentUser);

        List<UserNews> userNews = userNewsService.getNews(authorizedUser.getUserId());
        if (userNews != null) {
            return homeView.addObject("userNews", userNews);
        }
        return homeView;
    }

    @PostMapping("/home-page")
    public ModelAndView sendMessage(@SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser,
                                    @RequestParam(name = "deleteNews", required = false) String deleteNewsId,
                                    @RequestParam(name = "newsHeader", required = false) String newsHeader,
                                    @RequestParam(name = "newsDescription", required = false) String newsDescription) {

        ModelAndView homeView = new ModelAndView("home-page");
        UserDto currentUser = userService.getUserDtoByUserId(authorizedUser.getUserId());
        homeView.addObject("currentUser", currentUser);
        if (!newsDescription.isEmpty() && !newsHeader.isEmpty()) {
            userNewsService.addUserNews(authorizedUser.getUserId(), newsHeader, newsDescription, new Date());
        }
        if (deleteNewsId != null) {
            userNewsService.deleteNews(deleteNewsId);
        }

        List<UserNews> userNews = userNewsService.getNews(authorizedUser.getUserId());
        if (userNews != null) {
            return homeView.addObject("userNews", userNews);
        }
        return homeView;
    }

    @GetMapping("/home-page/logout")
    public ModelAndView logoutView(@SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {
        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("logout");
    }
}
