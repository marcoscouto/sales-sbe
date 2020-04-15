package io.github.marcoscouto.rest.controller;

import io.github.marcoscouto.domain.entity.User;
import io.github.marcoscouto.exception.PasswordInvalidException;
import io.github.marcoscouto.rest.dto.CredentialsDTO;
import io.github.marcoscouto.rest.dto.TokenDTO;
import io.github.marcoscouto.security.jwt.JwtService;
import io.github.marcoscouto.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }

    @PostMapping("/auth")
    public TokenDTO auth(@RequestBody CredentialsDTO credentials){
        try {
            User user = User
                    .builder()
                    .username(credentials.getUsername())
                    .password(credentials.getPassword())
                    .build();

            userService.authentication(user);

            String token = jwtService.generateToken(user);
            return new TokenDTO(user.getUsername(), token);
        } catch (UsernameNotFoundException | PasswordInvalidException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
