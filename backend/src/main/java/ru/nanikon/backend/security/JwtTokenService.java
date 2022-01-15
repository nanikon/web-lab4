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

    public String createToken(Long id) {
        long expirationTimeMillis = 600_000;
        Claims claims = Jwts.claims().setSubject(String.valueOf(id));
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
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // TODO a new exception throw new ProviderException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED);
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
