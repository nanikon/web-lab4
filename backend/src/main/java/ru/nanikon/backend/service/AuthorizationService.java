package ru.nanikon.backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nanikon.backend.data.TokenDTO;
import ru.nanikon.backend.data.UserDTO;
import ru.nanikon.backend.security.JwtTokenService;

/**
 * @author Natalia Nikonova
 */
@Service
public class AuthorizationService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationService(AuthenticationManager authenticationManager, UserService userService, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenDTO login(UserDTO userDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenService.createToken(userService.getIdByLogin(userDTO.getLogin()));
        return new TokenDTO(token);
    }

    public UserDTO register(UserDTO userDTO) {
        String password = userDTO.getPassword();
        userDTO.setPassword(passwordEncoder.encode(password));
        UserDTO result = userService.create(userDTO);
        result.setPassword(password);
        return result;
    }
}
