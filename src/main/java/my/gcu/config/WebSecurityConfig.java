package my.gcu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import my.gcu.services.UserService;

@Configuration
public class WebSecurityConfig {

    @Autowired
    @Lazy
    private UserService userService; 

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/images/**", "/service/**", "/register/**", "/login/**", "/css/**").permitAll() 
                .requestMatchers("/userInfo").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()) 
            
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .successHandler((request, response, authentication) -> {
                    // Use a String array for mutability in lambda
                    final String[] redirectUrl = { "/products" }; // Default redirect for non-admin users

                    // Check user roles and set redirect URL
                    authentication.getAuthorities().forEach(authority -> {
                        if (authority.getAuthority().equals("ROLE_ADMIN")) {
                            redirectUrl[0] = "/admin"; // Redirect admins to admin page
                        }
                    });

                    // Perform the redirection
                    response.sendRedirect(redirectUrl[0]);
                }))
            
            .logout(logout -> logout
                .logoutUrl("/logout") 
                .invalidateHttpSession(true) 
                .clearAuthentication(true)
                .permitAll()
                .logoutSuccessUrl("/"))

                 // Add this block to manage session behavior
                 .sessionManagement(session -> session
                    .maximumSessions(1) // Allow only one active session per user
                    .maxSessionsPreventsLogin(false) // Allows new logins to override old sessions
    );

        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
