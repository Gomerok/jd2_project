package by.academy.it.controller;

import by.academy.it.dto.AuthorizedUser;
import by.academy.it.dto.UserCommand;
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
        UserCommand currentUser = userService.getUserBySessionToken(authorizedUser.getSessionToken());
        return new ModelAndView("edit-profile")
                .addObject("currentUser", currentUser)
                .addObject("editUser", currentUser);
    }

    @PostMapping("/home-page/edit-profile.do")
    public ModelAndView editProfileViewDo(@ModelAttribute("editUser") @Valid UserCommand editUser,
                                          BindingResult result,
                                          @SessionAttribute("authorizedUser") AuthorizedUser authorizedUser,
                                          @SessionAttribute("currentUser") UserCommand currentUser,
                                          @RequestParam(name = "newProfileImage", required = false) MultipartFile newProfileImage) throws IOException {

        ModelAndView editView = new ModelAndView("edit-profile").addObject("editUser", editUser);
        if (result.hasErrors()) {
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
        userService.updateUser(editUser, authorizedUser.getSessionToken());

        return new ModelAndView("redirect:/home-page");
    }

}
