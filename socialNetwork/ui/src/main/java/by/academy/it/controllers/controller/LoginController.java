package by.academy.it.controllers.controller;

import by.academy.it.dto.AuthorizedUser;
import by.academy.it.dto.LoginUserDto;
import by.academy.it.service.UserService;
import by.academy.it.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("authorizedUser")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @GetMapping("/login")
    public ModelAndView userList(Model model) {
        return new ModelAndView("login")
                .addObject("loginUser", new LoginUserDto());
    }

    @PostMapping("/login.do")
    public ModelAndView login(@ModelAttribute("loginUser") @Valid LoginUserDto loginUserDto,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return new ModelAndView("login");
        }

        Map<String, String> errors = userValidator.loginUserValidator(loginUserDto);
        if (!errors.isEmpty()) {
            if (errors.get("loginError") != null) {
                result.addError(new FieldError("loginUser", "login", errors.get("loginError")));
            }
            if (errors.get("passwordError") != null) {
                result.addError(new FieldError("loginUser", "password", errors.get("passwordError")));
            }
            return new ModelAndView("login");
        }

        AuthorizedUser authorizedUser = userService.getUserIdAndUserRoleByLogin(loginUserDto.getLogin());
        if (userService.checkBlocked(authorizedUser.getUserId())) {
            return new ModelAndView("blocked_page");
        }
        return new ModelAndView("redirect:/home-page")
                .addObject("authorizedUser", authorizedUser);
    }
}
