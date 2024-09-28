package hakaton.webcommit.webCommit.entity.dto;


import hakaton.webcommit.webCommit.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "username cannot be blank")
    @Size(min = 4, max = 64,
            message = "username length should be between 4 and 64 characters")
    private String username;

    @Password(message = "password should be at least 8 characters long " +
            "and contain at least one lower case letter, " +
            "upper case letter, digit, symbol from @#$%^&+= " +
            "and not contain any other characters")
    private String password;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            message = "wrong format of email")
    private String email;
}
