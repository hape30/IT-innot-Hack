package hakaton.webcommit.webCommit.Controllers;

import hakaton.webcommit.webCommit.Entities.dto.UserInfo;
import hakaton.webcommit.webCommit.Entities.dto.UserRequest;
import hakaton.webcommit.webCommit.Services.UserService;
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
    public UserInfo getUser() {
        return userService.getCurrentUserInfo();
    }
}
