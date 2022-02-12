package by.academy.it.dto;

import lombok.Data;

@Data
public class AuthorizedUser {

    private String userId;
    private String userRole;

    public AuthorizedUser(String token, String role) {
        this.userId = token;
        this.userRole = role;
    }
}
