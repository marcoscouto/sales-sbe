package io.github.marcoscouto.service.impl;

import io.github.marcoscouto.domain.entity.User;
import io.github.marcoscouto.domain.repository.UserRepository;
import io.github.marcoscouto.exception.PasswordInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    public UserDetails authentication(User user){
        UserDetails userDetails = loadUserByUsername(user.getUsername());
        boolean passwordMatch = passwordEncoder.matches(user.getPassword(), userDetails.getPassword());
        if(passwordMatch) return userDetails;
        throw new PasswordInvalidException();
    }
}
