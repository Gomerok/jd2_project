package by.academy.it.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizedUser {

    private String userId;
    private String userRole;
}
