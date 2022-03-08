package ru.nanikon.backend.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.nanikon.backend.exception.BaseApiException;
import ru.nanikon.backend.exception.WrongTokenException;
import ru.nanikon.backend.security.JwtTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * @author Natalia Nikonova
 */
public class JwtFilter extends OncePerRequestFilter {
   private final JwtTokenService jwtTokenService;
   private final List<AntPathRequestMatcher> filterPaths = Arrays.asList(
           new AntPathRequestMatcher("/api/shots"),
           new AntPathRequestMatcher("/main"));

   public JwtFilter(JwtTokenService jwtTokenService) {
      this.jwtTokenService = jwtTokenService;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
      try {
         if (filterPaths.stream().anyMatch(path -> path.matches(request))) {
            String token = jwtTokenService.resolveToken(request);
            if (token != null && jwtTokenService.validateToken(token)) {
               Authentication auth = jwtTokenService.getAuthentication(token);
               SecurityContextHolder.getContext().setAuthentication(auth);
               filterChain.doFilter(request, response);
            } else {
               throw new WrongTokenException("Истекший или несуществующий JWT токен");
            }
         } else {
            filterChain.doFilter(request, response);
         }
      } catch (BaseApiException e) {
         handleBaseApiException(e, response);
      } catch (Exception e) {
         handleException(e, HttpServletResponse.SC_BAD_REQUEST, "BAD_REQUEST_ERROR", response);
      }
   }

   private void handleBaseApiException(BaseApiException exception, HttpServletResponse res) throws IOException {
      handleException(exception, exception.getHttpStatus().value(), exception.getErrorCode(), res);
   }

   private void handleException(Exception exception, int status, String errorCode, HttpServletResponse res) throws IOException {
      SecurityContextHolder.clearContext();
      res.resetBuffer();
      res.setCharacterEncoding("UTF-8");
      res.setStatus(status);
      res.setHeader("Content-Type", "application/json");
      PrintWriter out = res.getWriter();
      ObjectMapper mapper = new ObjectMapper();
      ObjectNode message = mapper.createObjectNode();
      message.put("errorCode", errorCode);
      message.put("errorMessage", exception.getMessage());
      out.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message));
   }
}
