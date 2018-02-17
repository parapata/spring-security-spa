package com.example.config.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.domain.Users;
import com.example.repository.UsersRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        Optional<Users> user = usersRepository.findById(account);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("ログイン情報が存在しません。");
        }
        return createUser(user.get());
    }

    /**
     * 認証ユーザの生成.
     *
     * @return 認証ユーザ
     */
    private LoginUser createUser(Users user) {
        return new LoginUser(user);
    }
}
