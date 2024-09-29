package com.umass.cs520.filter;

import com.umass.cs520.domain.Users;
import com.umass.cs520.service.UsersService;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
@Order(1)
public class CookieFilter extends GenericFilterBean {

  @Resource
  UsersService usersService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    // Get the userId cookie value
    String userId = null;
    Cookie[] cookies = httpRequest.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("user")) {
          userId = cookie.getValue();
          break;
        }
      }
    }

    Integer userIdInt = Optional.ofNullable(userId).map(Integer::parseInt).orElse(0);
    Users users = usersService.selectByPrimaryKey(userIdInt);

    // Set the userId value into the request context
    if (users != null) {
      httpRequest.setAttribute("user", users);
    }

    // Continue the filter chain
    chain.doFilter(request, response);
  }
}
