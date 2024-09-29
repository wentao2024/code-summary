package com.umass.cs520.controller.auth;

import com.umass.cs520.domain.ApiResponse;
import com.umass.cs520.domain.dto.EditPrivilegeRequestDTO;
import com.umass.cs520.domain.dto.ListUsersResponseDTO;
import com.umass.cs520.domain.dto.UserInfoDTO;
import com.umass.cs520.service.AuthService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userManagement")
public class UserManagementController {

    @Resource
    private AuthService authService;

    @GetMapping("/list")
    public ResponseEntity<?> listUsers() {
        List<UserInfoDTO> users = authService.listUsers();
        return ResponseEntity.ok(new ListUsersResponseDTO(true, users));
    }

    @PostMapping("/editPrivilege")
    public ResponseEntity<?> editUserPrivilege(@RequestBody EditPrivilegeRequestDTO requestDTO) {
        boolean success = authService.editUserPrivilege(requestDTO.getId(), requestDTO.getRole());
        if (success) {
            return ResponseEntity.ok(new ApiResponse(true, "User privileges updated successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Failed to update user privileges."));
        }
    }
}
