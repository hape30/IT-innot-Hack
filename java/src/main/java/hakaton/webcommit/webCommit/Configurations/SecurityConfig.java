package hakaton.webcommit.webCommit.Configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class SecurityConfig {

    private final String[] AUTH_WHITELIST_PATHS = new String[]{
            "/user/signup",
            "/token",
            "/token/update",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(hakaton.webcommit.webCommit.Repositories.UserRepository userRepository) {
        return (username) -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) //disable csrf for postman testing
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(AUTH_WHITELIST_PATHS).permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2.loginPage("/user/login").permitAll())
//                .formLogin(form -> form
//                        .loginPage("/user/login").permitAll()
//                        .defaultSuccessUrl("/user"))
                .logout(logout -> logout
                        .logoutUrl("/user/logout").permitAll())

                .build();
    }
}
