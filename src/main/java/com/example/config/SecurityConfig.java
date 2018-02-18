package com.example.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.example.config.auth.AuthenticationProviderImpl;
import com.example.config.auth.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example.config.auth")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationProviderImpl authenticationProvider;

    public SecurityConfig() {
        super();
    }

    public SecurityConfig(boolean disableDefaults) {
        super(disableDefaults);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/img/**", "/h2-console/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("guest")
                .password(passwordEncoder().encode("guest"))
                .roles("GUEST");

        auth.authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/login")
                .usernameParameter("userId")
                .passwordParameter("password")
                .loginProcessingUrl("/login/auth")
                .successHandler(getSuccessHandler())
                .failureUrl("/login-error")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .permitAll()
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID", "_ctkn")
                .invalidateHttpSession(true)
                .permitAll();

        http.rememberMe()
                .rememberMeCookieName("_remember")
                .rememberMeParameter("remember_me")
                .tokenValiditySeconds(30 * 24 * 60 * 60);

        CookieCsrfTokenRepository csrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        csrfTokenRepository.setCookieName("_ctkn");
        http.csrf().csrfTokenRepository(csrfTokenRepository);
    }

    private AuthenticationSuccessHandler getSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
                response.sendRedirect("/");
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
