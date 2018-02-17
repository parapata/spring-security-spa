package com.example.config.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.domain.Users;
import com.example.repository.UsersRepository;
import com.example.util.StringUtil;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(password)) {
            throw new AuthenticationCredentialsNotFoundException("user and password is required");
        }

        Optional<Users> user = usersRepository.findById(userId);
        if (!user.isPresent()) {
            throw new AuthenticationCredentialsNotFoundException("user not found");
        }
        Users users = user.get();
        if (!StringUtil.equals(passwordEncoder.encode(password), users.getPassword())) {
            throw new AuthenticationCredentialsNotFoundException("password unmatched");
        }

        return new UsernamePasswordAuthenticationToken(
                new LoginUser(users),
                password,
                authentication.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
