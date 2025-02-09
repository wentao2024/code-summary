package com.umass.cs520.domain.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListUsersResponseDTO {
    private boolean success;
    private List<UserInfoDTO> users;
}
