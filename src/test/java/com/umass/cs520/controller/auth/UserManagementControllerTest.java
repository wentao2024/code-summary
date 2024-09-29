package com.umass.cs520.controller.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.umass.cs520.domain.dto.UserInfoDTO;
import com.umass.cs520.service.AuthService;
import java.util.Collections;
import java.util.List;
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
@WebMvcTest(UserManagementController.class)
class UserManagementControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthService mockAuthService;

  @Test
  void testListUsers() throws Exception {
    // Setup
    // Configure AuthService.listUsers(...).
    final List<UserInfoDTO> userInfoDTOS = List.of(new UserInfoDTO("username", 0, 0));
    when(mockAuthService.listUsers()).thenReturn(userInfoDTOS);

    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(get("/api/userManagement/list")
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
  }

  @Test
  void testListUsers_AuthServiceReturnsNoItems() throws Exception {
    // Setup
    when(mockAuthService.listUsers()).thenReturn(Collections.emptyList());

    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(get("/api/userManagement/list")
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
  }

  @Test
  void testEditUserPrivilege() throws Exception {
    // Setup
    when(mockAuthService.editUserPrivilege(0, 0)).thenReturn(false);

    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(post("/api/userManagement/editPrivilege")
            .content("content").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
  }
}
