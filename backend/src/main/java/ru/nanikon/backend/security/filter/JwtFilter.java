package ru.nanikon.backend.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.nanikon.backend.security.JwtTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Natalia Nikonova
 */
public class JwtFilter extends OncePerRequestFilter {
   private final JwtTokenService jwtTokenService;
   private final AntPathRequestMatcher filterPath = new AntPathRequestMatcher("/swagger-ui/**");
   private final List<AntPathRequestMatcher> noFilterPath = Arrays.asList(
           new AntPathRequestMatcher("/swagger-ui/**"),
           new AntPathRequestMatcher("/v3/api-docs/**"),
           new AntPathRequestMatcher("/api/users/**"),
           new AntPathRequestMatcher("/echo"));

   public JwtFilter(JwtTokenService jwtTokenService) {
      this.jwtTokenService = jwtTokenService;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      if (noFilterPath.stream().anyMatch(path -> path.matches(request))) {
         filterChain.doFilter(request, response);
      } else {
         String token = jwtTokenService.resolveToken(request);
         if (token != null && jwtTokenService.validateToken(token)) {
            Authentication auth = jwtTokenService.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
         } else {
            throw new RuntimeException("uncorrect token in filter");
            //todo throw new WrongTokenException
         }
      }
   }
}
