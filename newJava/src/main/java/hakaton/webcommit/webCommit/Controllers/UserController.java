package hakaton.webcommit.webCommit.Controllers;

import hakaton.webcommit.webCommit.Entities.User;
import hakaton.webcommit.webCommit.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@Valid User user, Model model){
        if(!userService.createUser(user)){
            model.addAttribute("errorMessage","Пользователь с Email: " + user.getEmail() );
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmAccount(@RequestParam("token") String confirmationToken){
        return userService.confirmEmail(confirmationToken);
    }

}