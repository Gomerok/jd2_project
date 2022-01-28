package by.academy.it.controller;

import by.academy.it.dto.AuthorizedUser;
import by.academy.it.dto.SearchResult;
import by.academy.it.service.SearchService;
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

    @GetMapping(path = "/home-page/search-user/{pageid}")
    public ModelAndView searchView(@RequestParam(name = "param", required = false) String param,
                                   @PathVariable int pageid,
                                   @SessionAttribute(name = "str", required = false) String str,
                                   @SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {
        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }

        if (param != null) {
            str = param;
        }

        int start = pageid;
        int total = 5;
        if (start != 1) {
            start = (start - 1) * total + 1;
        }

        final List<SearchResult> results = searchService.searchAmongAll(str, start, total);
        ModelAndView modelAndView = new ModelAndView("search-user");
        modelAndView.addObject("results", results).addObject("pageid", pageid).addObject("str", str);
        return modelAndView;
    }
}
