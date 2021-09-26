package com.example.java.config;

import com.example.java.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        log.info("inside additionalAuthenticationChecks, userDetails is {}", userDetails);
    }

    @Autowired
    private UserService userService;

    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        log.info("inside retrieveUser, userName is {}", userName);
        Object token = usernamePasswordAuthenticationToken.getCredentials();
        if (userService.isTokenValid(String.valueOf(token))) {
            return new org.springframework.security.core.userdetails.User(String.valueOf(token)
                    , String.valueOf(token), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
        } else {
            log.info("Cannot find user with authentication token={}", token);
            throw new UsernameNotFoundException("Cannot find user with authentication token=" + token);
        }
    }
}
