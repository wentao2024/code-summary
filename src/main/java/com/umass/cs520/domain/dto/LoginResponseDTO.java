package com.umass.cs520.domain.dto;


import lombok.Data;

@Data
public class LoginResponseDTO {
    private boolean success;
    private String message;
    private boolean needChangePwd;
    
}
