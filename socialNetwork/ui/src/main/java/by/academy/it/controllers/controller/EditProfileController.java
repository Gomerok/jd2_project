package by.academy.it.controllers.controller;

import by.academy.it.dto.AuthorizedUser;
import by.academy.it.dto.UserValidDto;
import by.academy.it.service.UserService;
import by.academy.it.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@SessionAttributes("currentUser")
public class EditProfileController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/home-page/edit-profile")
    public ModelAndView editProfileView(@SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {
        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }
        if (userService.checkBlocked(authorizedUser.getUserId())) {
            return new ModelAndView("blocked_page");
        }
        UserValidDto currentUser = userService.getRegistrationUserByUserId(authorizedUser.getUserId());
        return new ModelAndView("edit-profile")
                .addObject("currentUser", currentUser)
                .addObject("editUser", currentUser);
    }

    @PostMapping("/home-page/edit-profile.do")
    public ModelAndView editProfileViewDo(@ModelAttribute("editUser") @Valid UserValidDto editUser,
                                          BindingResult result,
                                          @SessionAttribute("authorizedUser") AuthorizedUser authorizedUser,
                                          @SessionAttribute("currentUser") UserValidDto currentUser,
                                          @RequestParam(name = "newProfileImage", required = false) MultipartFile newProfileImage) throws IOException {

        ModelAndView editView = new ModelAndView("edit-profile").addObject("editUser", editUser);

        if(newProfileImage.getOriginalFilename().length()>60){
            result.addError(new FieldError("editUser", "profileImageName", "Image name too big, max=60 characters"));
        }
        if (result.hasErrors() && !result.hasFieldErrors("password")) {
            return editView;
        }

        if (!currentUser.getLogin().equals(editUser.getLogin())) {
            Map<String, String> errors = userValidator.addUserValidator(editUser);
            if (!errors.isEmpty()) {
                if (errors.get("existUserLogin") != null) {
                    result.addError(new FieldError("editUser", "login", errors.get("existUserLogin")));
                    return editView;
                }
            }
        }
        if (!currentUser.getEmail().equals(editUser.getEmail())) {
            Map<String, String> errors = userValidator.addUserValidator(editUser);
            if (!errors.isEmpty()) {
                if (errors.get("existUserEmail") != null) {
                    result.addError(new FieldError("editUser", "email", errors.get("existUserEmail")));
                    return editView;
                }
            }

        }
        if (newProfileImage.getOriginalFilename() != null && !newProfileImage.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + newProfileImage.getOriginalFilename();
            newProfileImage.transferTo(new File(uploadPath + "/" + resultFileName));
            editUser.setProfileImageName(resultFileName);
        }
        userService.updateUser(editUser, authorizedUser.getUserId());


        return new ModelAndView("redirect:/home-page");
    }

    @GetMapping("/home-page/edit-password")
    public ModelAndView editPasswordViewDo(@SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {
        if (authorizedUser == null) {
            return new ModelAndView("redirect:/");
        }
        if (userService.checkBlocked(authorizedUser.getUserId())) {
            return new ModelAndView("blocked_page");
        }
        return new ModelAndView("edit-password");
    }

    @PostMapping("/home-page/edit-password.do")
    public ModelAndView editPasswordView(@RequestParam("oldPassword") String oldPassword,
                                         @RequestParam("newPassword") String newPassword,
                                         @SessionAttribute(name = "authorizedUser", required = false) AuthorizedUser authorizedUser) {

        ModelAndView editPasswordView = new ModelAndView("edit-password");
        String invalidPassword = "Invalid password";
        if(!userValidator.checkPassword(oldPassword, authorizedUser.getUserId())){
            return editPasswordView.addObject("invalidPassword",invalidPassword);
        }

        String errorPassword = "Password cannot be empty min=8 max=25 characters";
        if(newPassword.length()<8){
            return editPasswordView.addObject("errorPassword",errorPassword);
        }

        userService.updateUserPassword(newPassword,authorizedUser.getUserId());

        return new ModelAndView("redirect:/home-page");
    }
}
