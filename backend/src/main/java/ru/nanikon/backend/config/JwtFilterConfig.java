package ru.nanikon.backend.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.nanikon.backend.security.JwtTokenService;
import ru.nanikon.backend.security.filter.JwtFilter;

/**
 * @author Natalia Nikonova
 */
public class JwtFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
   private final JwtTokenService jwtTokenService;

   public JwtFilterConfig(JwtTokenService jwtTokenService) {
      this.jwtTokenService = jwtTokenService;
   }

   @Override
   public void configure(HttpSecurity http) {
      JwtFilter customFilter = new JwtFilter(jwtTokenService);
      http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
   }
}
