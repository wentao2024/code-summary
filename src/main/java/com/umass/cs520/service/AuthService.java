package com.umass.cs520.service;

import com.umass.cs520.domain.Users;
import com.umass.cs520.domain.dto.LoginRequestDTO;
import com.umass.cs520.domain.dto.LoginResponseDTO;
import com.umass.cs520.domain.dto.UserInfoDTO;
import com.umass.cs520.util.RequestUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class AuthService {

  @Resource
  UsersService usersService;

  public LoginResponseDTO login(LoginRequestDTO request) {

    Users user = usersService.selectByUsernamePassword(request.getUsername(), request.getPassword());

    LoginResponseDTO response = new LoginResponseDTO();
    if (user != null) {
      if ("admin".equals(request.getPassword())) {
        response.setNeedChangePwd(true);
      }
      response.setMessage("success");
      response.setSuccess(true);

      ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      HttpServletResponse resp = ra.getResponse();

      Cookie cookie = new Cookie("user", user.getUserid() + "");
      cookie.setPath("/");
      resp.addCookie(cookie);
    }

    return response;
  }

  public void logout() {
    Cookie cookie = new Cookie("user", "");
    cookie.setMaxAge(0);
    cookie.setPath("/");
    ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletResponse response = ra.getResponse();
    response.addCookie(cookie);
  }

  public boolean registerUser(String username, String password) {
    Users user = new Users();
    user.setPassword(password);
    user.setUsername(username);
    usersService.insertSelective(user);
    return true;
  }

  public boolean changeAdminPassword(String currentPassword, String newPassword) {
    Users user = RequestUtil.getUser();

    if (user.getPassword().equals(currentPassword)) {
      user.setPassword(newPassword);
      usersService.updateByPrimaryKeySelective(user);
      return true;
    }

    return false;
  }

  public List<UserInfoDTO> listUsers() {
    List<Users> users = usersService.selectAllUsers();

    // Convert Users to UserInfoDTO
    return users.stream()
        .map(user -> new UserInfoDTO(user.getUsername(), user.getRole(), user.getUserid()))
        .collect(Collectors.toList());
  }

  public boolean editUserPrivilege(int id, int role) {
    Users user = new Users();
    user.setUserid(id);
    user.setRole(role);
    usersService.updateByPrimaryKeySelective(user);
    return true;
  }
}
