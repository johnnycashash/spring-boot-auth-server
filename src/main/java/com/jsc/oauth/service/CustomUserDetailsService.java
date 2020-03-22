package com.jsc.oauth.service;

import com.jsc.oauth.entity.Users;
import com.jsc.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

/**
 * The type Custom user details service.
 */
@Service
public class CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Find by username users.
     *
     * @param input the input
     * @return the users
     */
    public Users findByUsername(String input) {
        Users users = userRepository.findByUsername(input);

        if (users == null)
            throw new BadCredentialsException("Bad credentials");

        users.setAccountNonExpired(!users.isAccountNonExpired());
        users.setAccountNonLocked(!users.isAccountNonLocked());
        users.setCredentialsNonExpired(!users.isCredentialsNonExpired());
        new AccountStatusUserDetailsChecker().check(users);

        return users;
    }
}
