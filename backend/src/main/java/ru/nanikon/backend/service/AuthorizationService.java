package ru.nanikon.backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nanikon.backend.data.TokenDTO;
import ru.nanikon.backend.data.UserDTO;
import ru.nanikon.backend.entity.UserEntity;
import ru.nanikon.backend.security.JwtTokenService;

/**
 * @author Natalia Nikonova
 */
@Service
public class AuthorizationService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    public AuthorizationService(AuthenticationManager authenticationManager, UserService userService, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    public TokenDTO login(UserDTO userDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity user = userService.getUserByLogin(userDTO.getLogin());
        String token = jwtTokenService.createToken(user.getId());
        return new TokenDTO(token);
    }
}
