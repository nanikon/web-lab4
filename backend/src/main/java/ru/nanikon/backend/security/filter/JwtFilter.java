package ru.nanikon.backend.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.nanikon.backend.security.JwtTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Natalia Nikonova
 */
public class JwtFilter extends OncePerRequestFilter {
   private final JwtTokenService jwtTokenService;

   public JwtFilter(JwtTokenService jwtTokenService) {
      this.jwtTokenService = jwtTokenService;
   }
   
   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String token = jwtTokenService.resolveToken(request);
      if (token != null && jwtTokenService.validateToken(token)) {
         Authentication auth = jwtTokenService.getAuthentication(token);
         SecurityContextHolder.getContext().setAuthentication(auth);
      } /*else {
          todo throw new WrongTokenException
      }*/
   }
}
