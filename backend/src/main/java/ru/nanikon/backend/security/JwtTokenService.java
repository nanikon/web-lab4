package ru.nanikon.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.nanikon.backend.exception.NotFoundDataException;
import ru.nanikon.backend.exception.WrongTokenException;
import ru.nanikon.backend.security.userDetails.CustomUserDetailsService;
import ru.nanikon.backend.service.UserService;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Natalia Nikonova
 */
@Service
public class JwtTokenService {
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;

    public JwtTokenService(CustomUserDetailsService customUserDetailsService, UserService userService) {
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }

    public String createToken(Long id, String login) {
        long expirationTimeMillis = 600_000;
        Claims claims = Jwts.claims().setSubject(String.valueOf(id));
        claims.put("login", login);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTimeMillis);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public Long getUserIdByToken(String token) {
        return Long.valueOf(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject());
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userService.getUserById(getUserIdByToken(token)).getLogin());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            getUserIdByToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException | NotFoundDataException e) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
