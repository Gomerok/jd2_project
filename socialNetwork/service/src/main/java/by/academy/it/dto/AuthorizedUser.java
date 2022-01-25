package by.academy.it.dto;

import lombok.Data;

@Data
public class AuthorizedUser {

    private String sessionToken;
    private String userRole;

    public AuthorizedUser(String token, String role) {
        this.sessionToken = token;
        this.userRole = role;
    }
}
