package com.company.tms.auth.service;

import com.company.tms.auth.document.User;
import com.company.tms.auth.exception.UserNotFoundException;
import com.company.tms.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(username, username)
            .orElseThrow(() -> new UserNotFoundException("Username [{}] not found", username));

        return new org.springframework.security.core.userdetails.
            User(user.getUsername(), user.getPassword(),
                 AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", user.getRoles())));
    }
}