package com.umass.cs520.util;

import com.umass.cs520.domain.Users;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class RequestUtil {

  public static String getUserId() {
    return getCookieValue("user");
  }

  public static Users getUser() {
    ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = ra.getRequest();

    Users users = (Users) request.getAttribute("user");
    return users;
  }

  public static String getCookieValue(String cookieName) {
    ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = ra.getRequest();
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(cookieName)) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }
}
