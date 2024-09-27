package hakaton.webcommit.webCommit.Services;


import hakaton.webcommit.webCommit.Entities.ConfirmationToken;
import hakaton.webcommit.webCommit.Entities.Enums.Role;
import hakaton.webcommit.webCommit.Entities.User;
import hakaton.webcommit.webCommit.Repositories.ConfirmationTokenRepository;
import hakaton.webcommit.webCommit.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.mail.util.StreamProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    //@Autowired
    //private final PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;


    public boolean createUser(User user){
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("saving new user with email: {}", email);
        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here: " + "http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmToken());
        emailService.sendEmail(mailMessage);
        System.out.println("Confirmation Token: "+ confirmationToken.getConfirmToken());

        return true;

    }


    public ResponseEntity<?> confirmEmail(String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmToken(confirmationToken);

        if(token != null){
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.getRoles().add(Role.ROLE_USER);
            user.setActive(true);
            userRepository.save(user);
            return ResponseEntity.ok("Verify email successfuly!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify mail");
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
