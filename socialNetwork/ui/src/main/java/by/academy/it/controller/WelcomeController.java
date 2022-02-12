package by.academy.it.controller;

import by.academy.it.pojo.UserNews;
import by.academy.it.service.UserNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    private UserNewsService userNewsService;

    @GetMapping(path = {"/"})
    public ModelAndView home(HttpSession session) {
        session.removeAttribute("authorizedUser");


        List<UserNews> userNewsList = userNewsService.getWelcomePageNews("ROLE_ADMIN");

        return new ModelAndView("index").addObject("userNewsList",userNewsList);
    }
}



