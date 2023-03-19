package yourestack.epack.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPatternParser;
import yourestack.epack.business.service.CustomUserDetailsService;
import yourestack.epack.handlers.CustomAuthenticationFailureHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication //for role based authorization
@ComponentScan("yourestack.epack")
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {


    private final CustomUserDetailsService userDetailsService;

    private final AuthenticationProvider authenticationProvider;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setPatternParser(new PathPatternParser());
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("DaceBertina")
                .password(passwordEncoder.encode("DaceDace"))
                .roles("ADMIN")
                .build();
        UserDetails manager = User.withUsername("GintsBertins")
                .password(passwordEncoder.encode("GintsGints"))
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(admin, manager);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors();
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/registerClient", "/login*", "/authenticate", "api/v1/client/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/profile", "/profile1").authenticated())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/", "/signupForm", "/loginForm", "/v3/api-docs/**", "/swagger-ui/**", "/css/**", "/images/**").permitAll()
                        .requestMatchers("/manager/add"  ).hasRole("admin"))
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/perform_login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/profile1", true)
                .failureUrl("/login?error=true")
//                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler());
        return http.build();

    }
}
