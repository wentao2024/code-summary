package com.umass.cs520.domain.dto;

import lombok.Data;

@Data
public class ChangePasswordRequestDTO {

  private String currentPassword;
  private String newPassword;
}
