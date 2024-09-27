package hakaton.webcommit.webCommit.Services;


import hakaton.webcommit.webCommit.Entities.User;
import hakaton.webcommit.webCommit.Entities.dto.UserInfo;
import hakaton.webcommit.webCommit.Entities.dto.UserRequest;
import hakaton.webcommit.webCommit.Repositories.UserRepository;
import hakaton.webcommit.webCommit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        log.info("Getting user with id {}", userId);
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
    }

//    @Transactional
//    protected void updateUser(User user) {
//        Authentication newAuth = new UsernamePasswordAuthenticationToken(
//                user,
//                null,
//                user.getAuthorities()
//        );
//        SecurityContextHolder
//                .getContext()
//                .setAuthentication(newAuth);
//
//        userRepository.save(user);
//        log.info("Updated user {} with username {}", user.getId(), user.getUsername());
//    }

//    @Transactional
//    public void updateUser(UserRequest request) {
//        User updatedUser = mapToUser(request);
//        User user = retrieveCurrentUser();
//
//        if (!user.getId().equals(updatedUser.getId())) {
//            throw new AccessDeniedException("User id in request does not match current user id");
//        }
//
//        user.setUsername(updatedUser.getUsername());
//        user.setEmail(updatedUser.getEmail());
//        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
//
//        updateUser(user);
//    }

    @Transactional
    public void createUser(UserRequest request) {
        User user = mapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("Created user {} with username {}", user.getId(), user.getUsername());
    }

    public UserInfo getCurrentUserInfo() {
        log.info("Getting current user from security context");
        return mapper.map(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal(),
                UserInfo.class
        );
    }

}

//    @Transactional
//    public boolean createUser(UserRequest request) {
//        User user = mapper.map(request, User.class);
//        String email = user.getEmail();
//        if (userRepository.findByEmail(email) != null) return false;
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        log.info("saving new user with email: {}", email);
//        userRepository.save(user);
//
//        ConfirmationToken confirmationToken = new ConfirmationToken(user);
//
//        confirmationTokenRepository.save(confirmationToken);
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo(user.getEmail());
//        mailMessage.setSubject("Complete Registration!");
//        mailMessage.setText("To confirm your account, please click here: " + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmToken());
//        emailService.sendEmail(mailMessage);
//
//        return true;
//
//    }

//    public ResponseEntity<?> confirmEmail(String confirmationToken){
//        ConfirmationToken token = confirmationTokenRepository.findByConfirmToken(confirmationToken);
//
//        if(token != null){
//            User user = userRepository.findByEmail(token.getUser().getEmail());
//            user.getRoles().add(Role.ROLE_USER);
//            user.setActive(true);
//            userRepository.save(user);
//            return ResponseEntity.ok("Verify email successfuly!");
//        }
//        return ResponseEntity.badRequest().body("Error: Couldn't verify mail");
//    }
//    public User getUserByPrincipal(Principal principal) {
//        if (principal == null) return new User();
//        return userRepository.findByEmail(principal.getName());
//    }
//}
