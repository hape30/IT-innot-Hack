package hakaton.webcommit.webCommit.Configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;


@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


   // @Bean
//    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/**", "/registration", "/confirm-account", "/static/**").permitAll()
//                        .requestMatchers("/**")
//                        .hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
//                        .requestMatchers("/panel").hasAnyAuthority("ROLE_ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout((logout) -> logout.permitAll());
//
//        return http.build();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new  BCryptPasswordEncoder(8);
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}