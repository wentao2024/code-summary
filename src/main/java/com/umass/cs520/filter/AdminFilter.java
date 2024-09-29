package com.umass.cs520.filter;

import com.umass.cs520.domain.Users;
import com.umass.cs520.util.RequestUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.web.filter.GenericFilterBean;

public class AdminFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        Users user = RequestUtil.getUser();
        boolean isAdmin = user != null && user.getRole() == 1;
        if (!isAdmin) {

        }

        // If the user is an admin, continue the filter chain
        chain.doFilter(request, response);
    }
}
