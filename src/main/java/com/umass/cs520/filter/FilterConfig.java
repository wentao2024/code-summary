package com.umass.cs520.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

  @Bean
  public FilterRegistrationBean<AdminFilter> adminFilter() {
    FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new AdminFilter());
    registrationBean.addUrlPatterns("/api/userManagement/*");
    return registrationBean;
  }
}
