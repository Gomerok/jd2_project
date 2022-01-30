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


    @GetMapping(path = "/home-page/search-user/{pageId}")
    public ModelAndView searchView(@RequestParam(name = "param", required = false) String param,
                                   @RequestParam(name = "userId", required = false) String userId,
                                   @PathVariable("pageId") int pageId,
                                   @SessionAttribute(name = "str", required = false) String str,
                                   @SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {
        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }

        if (param != null) {
            str = param;
        }
        int pageSize = 5;
        int pageCount = (int) (Math.ceil(searchService.pageCount(str)) / pageSize + 1);
        final List<SearchResult> results = searchService.searchAmongAll(str, pageId, pageSize);
        if (userId == null) {
            ModelAndView modelAndView = new ModelAndView("search-user");
            modelAndView.addObject("results", results)
                    .addObject("pageId", pageId)
                    .addObject("str", str)
                    .addObject("pageCount", pageCount);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("user-info");

            SearchResult user = results.stream()
                    .filter(userById -> userId.equals(userById.getId()))
                    .findAny()
                    .orElse(null);

            return modelAndView.addObject("user", user);
        }
    }
}
