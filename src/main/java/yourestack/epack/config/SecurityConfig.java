package yourestack.epack.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPatternParser;
import yourestack.epack.business.service.CustomUserDetailsService;
import yourestack.epack.handlers.CustomAuthenticationFailureHandler;

import java.io.IOException;


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
                .password(passwordEncoder.encode("DaceDace1*"))
                .roles("ADMIN")
                .build();
        UserDetails manager = User.withUsername("GintsBertins")
                .password(passwordEncoder.encode("GintsGints2$"))
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(admin, manager);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    public void configure(WebSecurity security){
        security.ignoring().requestMatchers("/css/**","/images/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors();
        http
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST,"/error", "/registerClient", "/login*", "/order").permitAll()
                        .requestMatchers("/orderForm", "/profile1").authenticated())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.GET, "/", "/signupForm", "/loginForm", "/aboutus", "/v3/api-docs/**", "/swagger-ui/**",
                                "/css/**", "/images/**", "/allEpacks", "/javaInfo").permitAll()
                        .requestMatchers("/manager/add"  ).hasRole("ADMIN"))
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/perform_login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/perform_logout"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/logoutForm")
                .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler())
               .and();
        http.csrf().disable(); //must be configured; for now disabled because it is conflicting with savedRequest function
        return http.build();
    }
}
