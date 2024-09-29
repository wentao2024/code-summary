package com.umass.cs520.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.umass.cs520.domain.Users;
import com.umass.cs520.domain.dto.LoginRequestDTO;
import com.umass.cs520.domain.dto.LoginResponseDTO;
import com.umass.cs520.domain.dto.UserInfoDTO;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  @Mock
  private UsersService mockUsersService;

  private AuthService authServiceUnderTest;

  @BeforeEach
  void setUp() {
    authServiceUnderTest = new AuthService();
    authServiceUnderTest.usersService = mockUsersService;
  }

  @Test
  void testLogin() {
    // Setup
    final LoginRequestDTO request = new LoginRequestDTO();
    request.setUsername("username");
    request.setPassword("password");

    final LoginResponseDTO expectedResult = new LoginResponseDTO();
    expectedResult.setSuccess(false);
    expectedResult.setMessage("success");
    expectedResult.setNeedChangePwd(false);

    // Configure UsersService.selectByUsernamePassword(...).
    final Users users = new Users();
    users.setUserid(0);
    users.setUsername("username");
    users.setPassword("newPassword");
    users.setRole(0);
    when(mockUsersService.selectByUsernamePassword("username", "password")).thenReturn(users);

    // Run the test
    final LoginResponseDTO result = authServiceUnderTest.login(request);

    // Verify the results
    assertThat(result).isEqualTo(expectedResult);
  }

  @Test
  void testLogin_UsersServiceReturnsNull() {
    // Setup
    final LoginRequestDTO request = new LoginRequestDTO();
    request.setUsername("username");
    request.setPassword("password");

    final LoginResponseDTO expectedResult = new LoginResponseDTO();
    expectedResult.setSuccess(false);
    expectedResult.setMessage("success");
    expectedResult.setNeedChangePwd(false);

    when(mockUsersService.selectByUsernamePassword("username", "password")).thenReturn(null);

    // Run the test
    final LoginResponseDTO result = authServiceUnderTest.login(request);

    // Verify the results
    assertThat(result).isEqualTo(expectedResult);
  }

  @Test
  void testLogout() {
    // Setup
    // Run the test
    authServiceUnderTest.logout();

    // Verify the results
  }

  @Test
  void testRegisterUser() {
    // Setup
    // Run the test
    final boolean result = authServiceUnderTest.registerUser("username", "newPassword");

    // Verify the results
    assertThat(result).isTrue();

    // Confirm UsersService.insertSelective(...).
    final Users record = new Users();
    record.setUserid(0);
    record.setUsername("username");
    record.setPassword("newPassword");
    record.setRole(0);
    verify(mockUsersService).insertSelective(record);
  }

  @Test
  void testChangeAdminPassword() {
    // Setup
    // Run the test
    final boolean result = authServiceUnderTest.changeAdminPassword("currentPassword", "newPassword");

    // Verify the results
    assertThat(result).isTrue();

    // Confirm UsersService.updateByPrimaryKeySelective(...).
    final Users record = new Users();
    record.setUserid(0);
    record.setUsername("username");
    record.setPassword("newPassword");
    record.setRole(0);
    verify(mockUsersService).updateByPrimaryKeySelective(record);
  }

  @Test
  void testListUsers() {
    // Setup
    final List<UserInfoDTO> expectedResult = List.of(new UserInfoDTO("username", 0, 0));

    // Configure UsersService.selectAllUsers(...).
    final Users users1 = new Users();
    users1.setUserid(0);
    users1.setUsername("username");
    users1.setPassword("newPassword");
    users1.setRole(0);
    final List<Users> users = List.of(users1);
    when(mockUsersService.selectAllUsers()).thenReturn(users);

    // Run the test
    final List<UserInfoDTO> result = authServiceUnderTest.listUsers();

    // Verify the results
    assertThat(result).isEqualTo(expectedResult);
  }

  @Test
  void testListUsers_UsersServiceReturnsNoItems() {
    // Setup
    when(mockUsersService.selectAllUsers()).thenReturn(Collections.emptyList());

    // Run the test
    final List<UserInfoDTO> result = authServiceUnderTest.listUsers();

    // Verify the results
    assertThat(result).isEqualTo(Collections.emptyList());
  }

  @Test
  void testEditUserPrivilege() {
    // Setup
    // Run the test
    final boolean result = authServiceUnderTest.editUserPrivilege(0, 0);

    // Verify the results
    assertThat(result).isTrue();

    // Confirm UsersService.updateByPrimaryKeySelective(...).
    final Users record = new Users();
    record.setUserid(0);
    record.setUsername("username");
    record.setPassword("newPassword");
    record.setRole(0);
    verify(mockUsersService).updateByPrimaryKeySelective(record);
  }
}
