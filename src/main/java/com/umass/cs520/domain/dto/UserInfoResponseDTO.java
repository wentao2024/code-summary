package com.umass.cs520.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponseDTO {
    private boolean success;
    private UserInfoDTO userInfo;
}
