package by.academy.it.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchResult {

    private String firstName;

    private String lastName;

    private String gender;

    private String profileImageName;

    private String profileText;
}
