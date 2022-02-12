package by.academy.it.dto;

import lombok.Data;

import javax.validation.constraints.Size;


@Data
public class LoginUserDto {

    @Size(min = 2, max = 15, message = "Incorrect login, min=2 max=15 characters")
    private String login;

    @Size(min = 4, max = 25, message = "Incorrect password, min=8 max=25 characters")
    private String password;

}
