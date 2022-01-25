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
public class EditProfileController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    public static ModelAndView editView = new ModelAndView("edit-profile");

    @GetMapping("/home-page/edit-profile")
    public ModelAndView editProfileView(@SessionAttribute("authorizedUser") AuthorizedUser authorizedUser) {
        UserCommand currentUser = userService.getUserBySessionToken(authorizedUser.getSessionToken());
        return new ModelAndView("edit-profile").addObject("editUser", currentUser);
    }

    @PostMapping("/home-page/edit-profile.do")
    public ModelAndView editProfileViewDo(@ModelAttribute("editUser") @Valid UserCommand editUserCommand,
                                          BindingResult result,
                                          @RequestParam(name = "newProfileImage", required = false) MultipartFile newProfileImage) throws IOException {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError("password"));
            System.out.println(editUserCommand);
            return new ModelAndView("edit-profile");
        }

        Map<String, String> errors = userValidator.addUserValidator(editUserCommand);
        if (!errors.isEmpty()) {
            if (errors.get("existUserLogin") != null) {
                result.addError(new FieldError("editUser", "login", errors.get("existUserLogin")));
            }
            if (errors.get("existUserEmail") != null) {
                result.addError(new FieldError("editUser", "email", errors.get("existUserEmail")));
            }
            return new ModelAndView("edit-profile");
        }

        if (newProfileImage.getOriginalFilename() != null && !newProfileImage.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + newProfileImage.getOriginalFilename();
            newProfileImage.transferTo(new File(uploadPath + "/" + resultFileName));
            editUserCommand.setProfileImageName(resultFileName);
        }
//        userService.createUser(editUserCommand);

        return new ModelAndView("redirect:/home-page");
    }

}
