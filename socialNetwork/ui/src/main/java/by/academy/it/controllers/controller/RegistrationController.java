package by.academy.it.controllers.controller;

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
public class RegistrationController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public ModelAndView registrationView() {
        return new ModelAndView("registration").addObject("newUser", new UserValidDto());
    }

    @PostMapping("/registration.do")
    public ModelAndView registrationViewDo(@ModelAttribute("newUser") @Valid UserValidDto newUser,
                                           BindingResult result,
                                           @RequestParam("profileImage") MultipartFile profileImage
    ) throws IOException {

        if(profileImage.getOriginalFilename().length()>60){
            result.addError(new FieldError("editUser", "profileImageName", "Image name too big, max=60 characters"));
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration").addObject("profileImage",profileImage);
        }

        Map<String, String> errors = userValidator.addUserValidator(newUser);
        if (!errors.isEmpty()) {
            if (errors.get("existUserLogin") != null) {
                result.addError(new FieldError("newUser", "login", errors.get("existUserLogin")));
            }
            if (errors.get("existUserEmail") != null) {
                result.addError(new FieldError("newUser", "email", errors.get("existUserEmail")));
            }
            return new ModelAndView("registration");
        }

        if (profileImage.getOriginalFilename() != null && !profileImage.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + profileImage.getOriginalFilename();
            profileImage.transferTo(new File(uploadPath + "/" + resultFileName));
            newUser.setProfileImageName(resultFileName);
        }
        userService.saveUser(newUser);

        return new ModelAndView("redirect:/login");


    }

}