package hakaton.webcommit.webCommit.controller;

import hakaton.webcommit.webCommit.entity.dto.UserInfo;
import hakaton.webcommit.webCommit.entity.dto.UserRequest;
import hakaton.webcommit.webCommit.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@Valid @RequestBody UserRequest request) {
        userService.createUser(request);
    }

    @GetMapping("")
    @SecurityRequirement(name = "SessionAuth")
    public UserInfo getUser() {
        return userService.getCurrentUserInfo();
    }

    // for documenting logout endpoint
    @PostMapping("/logout")
    @Operation(summary = "Logout current user")
    @SecurityRequirement(name = "SessionAuth")
    public void logout() {
    }

    // for documenting login endpoint
    // I use request params to , but I couldn't find a way to represent it in Swagger
    @PostMapping(value = "/login")
    @Operation(
            summary = "Login user",
            description = "Spring uses form-data for login by default, " +
                    "but I couldn't find a way to represent it in Swagger, " +
                    "you can find the right request format in the postman documentation")
    public void login(
            @RequestParam("username")
            @Parameter(description = "User email. " +
                    "I use email instead of username, because it's unique in my db schema",
                    example = "email@mail.com"
            )
            String email,

            @RequestParam("password")
            @Parameter(description = "User password", example = "StrongPassword123$")
            String password
    ) {
    }
}
