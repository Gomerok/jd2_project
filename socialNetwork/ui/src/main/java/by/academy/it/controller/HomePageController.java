package by.academy.it.controller;

import by.academy.it.dto.AuthorizedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomePageController {

    @GetMapping("/home-page")
    public ModelAndView homeView(@SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {

        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("home-page");
    }

    @GetMapping("/home-page/logout")
    public ModelAndView logoutView(@SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {

        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("logout");
    }
}
