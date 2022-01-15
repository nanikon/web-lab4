package ru.nanikon.backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nanikon.backend.data.TokenDTO;
import ru.nanikon.backend.data.UserDTO;

/**
 * @author Natalia Nikonova
 */
@Service
public class AuthorizationService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthorizationService(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public TokenDTO login(UserDTO userDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new TokenDTO("");
    }
}
