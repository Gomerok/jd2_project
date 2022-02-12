package by.academy.it.controllers.controller;

import by.academy.it.dto.AuthorizedUser;
import by.academy.it.dto.UserDto;
import by.academy.it.service.SearchService;
import by.academy.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes("str")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/home-page/search-user/{pageId}")
    public ModelAndView searchView(@RequestParam(name = "param", required = false) String param,
                                   @RequestParam(name = "adminAction", required = false) String adminAction,
                                   @PathVariable("pageId") int pageId,
                                   @SessionAttribute(name = "str", required = false) String str,
                                   @SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {
        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }
        if (userService.checkBlocked(authorizedUser.getUserId())) {
            return new ModelAndView("blocked_page");
        }

        ModelAndView modelAndView = new ModelAndView("search-user");

        if (param != null) {
            str = param;
        }

        if (adminAction != null) {
            String[] action = adminAction.split(" ");

            if (action[0].equals("DELETE")) {
                userService.deleteUser(action[1]);
            }

            if (action[0].equals("BLOCKED")) {
                userService.blockedOrActiveUser(action[1], "ACTIVE");
            }
            if (action[0].equals("ACTIVE")) {
                userService.blockedOrActiveUser(action[1], "BLOCKED");
            }
        }
        int pageSize = 5;
        int pageCount = (int) (Math.ceil(searchService.pageCount(str)) / pageSize + 1);
        final List<UserDto> results = searchService.searchAmongAll(authorizedUser.getUserId(),str, pageId, pageSize);


        modelAndView.addObject("results", results)
                .addObject("pageId", pageId)
                .addObject("str", str)
                .addObject("pageCount", pageCount);
        return modelAndView;

    }
}
