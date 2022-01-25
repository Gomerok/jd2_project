package by.academy.it.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomePageController {

    @GetMapping("/home-page")
    public ModelAndView homeView() {
        return new ModelAndView("home-page");
    }

    @GetMapping("/home-page/logout")
    public ModelAndView logoutView() {
        return new ModelAndView("logout");
    }
}
