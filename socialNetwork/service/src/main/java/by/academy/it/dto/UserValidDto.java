package by.academy.it.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserValidDto {

    @Size(min = 0, max = 15, message = "First name min=2 max=15 characters")
    private String firstName;

    @Size(min = 0, max = 15, message = "Last name empty min=2 max=15 characters")
    private String lastName;

    @Size(min = 2, max = 15, message = "Login cannot be empty min=2 max=15 characters")
    private String login;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email incorrect")
    private String email;

    @NotBlank(message = "Choose a gender")
    private String gender;

    @Size(min = 8, max = 25, message = "Password cannot be empty min=8 max=25 characters")
    private String password;

    private String profileImageName;

    @Size(min = 0, max = 250, message = "Maximum number of characters 250")
    private String profileText;

}

