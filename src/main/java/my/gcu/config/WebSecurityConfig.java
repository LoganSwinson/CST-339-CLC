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

/**
 * Configuration class for setting up web security using Spring Security. It
 * defines authentication, authorization, and session management settings.
 */
@Configuration
public class WebSecurityConfig {

    /**
     * Service for managing user authentication and user details.
     */
    @Autowired
    @Lazy
    private UserService userService;

    /**
     * Configures the security filter chain to handle HTTP requests, login, and
     * logout behavior.
     *
     * @param http the {@link HttpSecurity} object for customizing security
     * settings
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if any security configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/images/**", "/service/**", "/register/**", "/login/**", "/css/**").permitAll() // Public endpoints
                .requestMatchers("/userInfo").hasAnyRole("USER", "ADMIN") // Accessible by users and admins
                .requestMatchers("/admin", "/admin/**").hasRole("ADMIN") // Admin-only endpoints
                .anyRequest().authenticated()) // All other requests require authentication
                .formLogin(form -> form
                .loginPage("/login") // Custom login page
                .usernameParameter("username") // Username parameter
                .passwordParameter("password") // Password parameter
                .permitAll()
                .successHandler((request, response, authentication) -> {
                    final String[] redirectUrl = {"/products"}; // Default redirect URL
                    authentication.getAuthorities().forEach(authority -> {
                        if (authority.getAuthority().equals("ROLE_ADMIN")) {
                            redirectUrl[0] = "/admin"; // Redirect admins to the admin page
                        }
                    });
                    response.sendRedirect(redirectUrl[0]); // Perform redirection
                }))
                .logout(logout -> logout
                .logoutUrl("/logout") // Logout URL
                .invalidateHttpSession(true) // Invalidate session on logout
                .clearAuthentication(true) // Clear authentication
                .permitAll()
                .logoutSuccessUrl("/")) // Redirect to home page after logout
                .sessionManagement(session -> session
                .maximumSessions(1) // Limit to one active session per user
                .maxSessionsPreventsLogin(false)); // Allow new logins to override old sessions

        return http.build();
    }

    /**
     * Configures the authentication manager to use a custom user details
     * service and a password encoder for handling user authentication.
     *
     * @param auth the {@link AuthenticationManagerBuilder} for managing
     * authentication
     * @throws Exception if any configuration fails
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    /**
     * Creates a password encoder bean for encoding and verifying passwords
     * securely.
     *
     * @return a {@link BCryptPasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
