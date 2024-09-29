package com.umass.cs520.controller.auth;

import com.umass.cs520.domain.ApiResponse;
import com.umass.cs520.domain.Users;
import com.umass.cs520.domain.dto.ChangePasswordRequestDTO;
import com.umass.cs520.domain.dto.LoginRequestDTO;
import com.umass.cs520.domain.dto.LoginResponseDTO;
import com.umass.cs520.domain.dto.LogoutResponseDTO;
import com.umass.cs520.domain.dto.RegisterRequestDTO;
import com.umass.cs520.domain.dto.UserInfoDTO;
import com.umass.cs520.domain.dto.UserInfoResponseDTO;
import com.umass.cs520.service.AuthService;
import com.umass.cs520.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired
  private AuthService authService;

  @RequestMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
    LoginResponseDTO response = authService.login(request);
    return ResponseEntity.ok(response);
  }

  @RequestMapping("/logout")
  public ResponseEntity<LogoutResponseDTO> logout() {
    authService.logout();
    return ResponseEntity.ok(new LogoutResponseDTO());
  }


  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO requestDTO) {
    boolean success = authService.registerUser(requestDTO.getUsername(), requestDTO.getPassword());
    if (success) {
      return ResponseEntity.ok(new ApiResponse(true, "Registration successful"));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Registration failed"));
    }
  }

  @PostMapping("/changeAdminPassword")
  public ResponseEntity<?> changeAdminPassword(@RequestBody ChangePasswordRequestDTO requestDTO) {
    boolean success = authService.changeAdminPassword(requestDTO.getCurrentPassword(), requestDTO.getNewPassword());
    if (success) {
      return ResponseEntity.ok(new ApiResponse(true, "Password changed successfully"));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Password change failed"));
    }
  }

  @GetMapping("/userinfo")
  public ResponseEntity<?> getUserInfo() {
    Users user = RequestUtil.getUser();

    UserInfoDTO userInfo = new UserInfoDTO();
    userInfo.setRole(user.getRole());
    userInfo.setUsername(user.getUsername());

    if (userInfo != null) {
      return ResponseEntity.ok(new UserInfoResponseDTO(true, userInfo));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Failed to get user info"));
    }
  }

}
