package com.umass.cs520.controller.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.umass.cs520.domain.dto.LoginRequestDTO;
import com.umass.cs520.domain.dto.LoginResponseDTO;
import com.umass.cs520.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthService mockAuthService;

  @Test
  void testLogin() throws Exception {
    // Setup
    // Configure AuthService.login(...).
    final LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
    loginResponseDTO.setSuccess(false);
    loginResponseDTO.setMessage("message");
    loginResponseDTO.setNeedChangePwd(false);
    final LoginRequestDTO request = new LoginRequestDTO();
    request.setUsername("username");
    request.setPassword("password");
    when(mockAuthService.login(request)).thenReturn(loginResponseDTO);

    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(get("/api/login")
            .content("content").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
  }

  @Test
  void testLogout() throws Exception {
    // Setup
    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(get("/api/logout")
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    verify(mockAuthService).logout();
  }

  @Test
  void testRegisterUser() throws Exception {
    // Setup
    when(mockAuthService.registerUser("username", "password")).thenReturn(false);

    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(post("/api/register")
            .content("content").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
  }

  @Test
  void testChangeAdminPassword() throws Exception {
    // Setup
    when(mockAuthService.changeAdminPassword("currentPassword", "newPassword")).thenReturn(false);

    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(post("/api/changeAdminPassword")
            .content("content").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
  }

  @Test
  void testGetUserInfo() throws Exception {
    // Setup
    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(get("/api/userinfo")
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
  }
}
